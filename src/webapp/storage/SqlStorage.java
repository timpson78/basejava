package webapp.storage;

import webapp.exeption.NotExistStorageExeption;
import webapp.model.*;
import webapp.sql.SqlHelper;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM  resume");
    }

    @Override
    public void update(Resume r) {

        sqlHelper.transactionalExecute(conn -> {
            String uuid = r.getUuid();
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageExeption(r.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact where resume_uuid=?")) {
                ps.setString(1, uuid);
                ps.execute();
            }

            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section where resume_uuid=?")) {
                ps.setString(1, uuid);
                ps.execute();
            }

            insertContacts(conn, r);
            insertSections(conn, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {

        sqlHelper.transactionalExecute(conn -> {

            String uuid = r.getUuid();
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid,full_name) VALUES(?,?)")) {
                ps.setString(1, uuid);
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContacts(conn, r);
            insertSections(conn, r);
            return null;
        });

    }


    @Override
    public Resume get(String uuid) {

        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * from resume r WHERE r.uuid=? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageExeption(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
                setContacts(uuid, conn, r);
                setSections(uuid, conn, r);
                return r;
            }
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageExeption(uuid);
            }
            return null;
        });

    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            String uuid, full_name;
            List<Resume> list = new ArrayList();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    uuid = rs.getString("uuid");
                    full_name = rs.getString("full_name");
                    if (uuid != null) {
                        r = new Resume(uuid, full_name);
                        setContacts(uuid, conn, r);
                        setSections(uuid, conn, r);
                        list.add(r);
                    }
                }
            }
            return list;
        });
    }


    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(uuid) FROM resume ", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES(?,?,?)")) {

            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Connection conn, Resume r) throws SQLException {

        PreparedStatement psInsert = conn.prepareStatement("INSERT INTO section (resume_uuid,type,value) VALUES (?,?,?)");

        for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
            SectionType sectionType = e.getKey();
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    insertSection(conn, r, sectionType,psInsert);
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    insertSection(conn, r, sectionType,psInsert);
                    break;
             /*   case EDUCATION:
                case EXPERIENCE:
                    OrganizationSection organizationSection = (OrganizationSection) r.getSection(sectionType);
                    List<Organization> orgList = organizationSection.getOrgList();
                    for (Organization organization : orgList) {
                        Link link = organization.getOrgLink();
                        List<Organization.Position> posList = organization.getPositions();
                        for (Organization.Position pos: posList) {
                            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO organization (resume_uuid,linkname,linkurl,type,title_position,description_position,startdate_position,enddate_position) " +
                                    "VALUES (?,?,?,?,?,?,Date(?),Date(?))")) {
                                ps.setString(1, r.getUuid());
                                ps.setString(2,link.getLinkName());
                                ps.setString(3,link.getUrlName());
                                ps.setString(4,sectionType.name());
                                ps.setString(5,pos.getTitle());
                                ps.setString(6,pos.getDescription());
                                ps.setString(7,pos.getStartDate().toString());
                                ps.setString(8,pos.getEndDate().toString());
                                ps.execute();
                            }
                        }
                    }
                    break;*/
                case EXPERIENCE:
                    break;
                case EDUCATION:
                    break;
            }
        }
        psInsert.executeBatch();
    }

    private void insertSection(Connection conn, Resume r, SectionType sectionType, PreparedStatement ps) throws SQLException {
            ps.setString(1, r.getUuid());
            ps.setString(2, sectionType.name());
            if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                TextSection textSection = (TextSection) r.getSection(sectionType);
                ps.setString(3, textSection.getText());
            } else if ((sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS)) {
                ListSection listSection = (ListSection) r.getSection(sectionType);
                ps.setString(3, listSection.getAllString("\n"));
            }
            ps.addBatch();
    }

    private void setSections(String uuid, Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT s.type, s.value FROM section s WHERE resume_uuid=?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String valueSection = rs.getString("value");
                SectionType sectionType = SectionType.valueOf(rs.getString("type"));
                if (valueSection != null && sectionType != null) {
                    switch (sectionType) {
                        case PERSONAL:
                        case OBJECTIVE:
                            r.addSection(sectionType, new TextSection(valueSection));
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            r.addSection(sectionType, new ListSection(Arrays.asList(valueSection.split("\n"))));
                            break;

                    }
                }
            }
            // setOrganization(uuid, conn, r,"EXPERIENCE");
            // setOrganization(uuid, conn, r,"EDUCATION");
        }
    }


    private void setContacts(String uuid, Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact c WHERE resume_uuid=?")) {
            ps.setString(1, uuid);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                r.addContact(ContactType.valueOf(rs1.getString("type")), rs1.getString("value"));
            }
        }
    }

    private void setOrganization(String uuid, Connection conn, Resume r, String type) throws SQLException {
        LinkedHashMap<String, Organization> map = new LinkedHashMap<>();

        try (PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM organization o WHERE o.resume_uuid=? and o.type=?")) {
            ps1.setString(1, uuid);
            ps1.setString(2, type);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                String linkname = rs1.getString("linkname");
                String linkurl = rs1.getString("linkurl");
                LocalDate startDate = LocalDate.parse(rs1.getString("startdate_position").substring(0, 10));
                LocalDate endDate = LocalDate.parse(rs1.getString("enddate_position").substring(0, 10));
                String title = rs1.getString("title_position");
                String description = rs1.getString("description_position");

                Organization org = map.get(linkname);
                if (org == null) {
                    org = new Organization(linkname, linkurl, new ArrayList<Organization.Position>());
                    map.put(linkname, org);
                }
                org.setPosition(new Organization.Position(startDate, endDate, title, description));
            }

        }
        r.addSection(SectionType.valueOf(type), new OrganizationSection(new ArrayList<Organization>(map.values())));
        return;
    }
}

