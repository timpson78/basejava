package webapp.storage;

import webapp.exeption.NotExistStorageExeption;
import webapp.exeption.StorageExeption;
import webapp.model.ContactType;
import webapp.model.Resume;
import webapp.sql.ConnectionFactory;
import webapp.sql.SqlExecutor;
import webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
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

            insertContacts(conn,r);
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

            insertContacts(conn,r);
            return null;
        });

    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT r.uuid,r.full_name,c.type,c.value FROM resume r " +
                " LEFT JOIN contact c ON r.uuid=c.resume_uuid " +
                " WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageExeption(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));

            do {
                String value = rs.getString("value");
                if (value != null) {
                    ContactType type = ContactType.valueOf(rs.getString("type"));
                    r.addContact(type, value);
                }
            }
            while (rs.next());
            return r;
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
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c  ON r.uuid=c.resume_uuid  " +
                " ORDER BY r.full_name", ps -> {
            ResultSet rs = ps.executeQuery();
           LinkedHashMap<String, Resume> map = new LinkedHashMap<>();
            String uuid, type, value;
            while (rs.next()) {
                uuid = rs.getString("uuid");
                Resume resume = map.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, rs.getString("full_name"));
                    map.put(uuid, resume);
                }
                type = rs.getString("type");
                value = rs.getString("value");
                if ((type != null) || (value != null)) {
                    resume.addContact(ContactType.valueOf(type), value);
                }

            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(uuid) FROM resume ", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
    public void insertContacts(Connection conn, Resume r ) throws SQLException {
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
}


