package webapp.storage.StrategyPattern;

import webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataStreamSerializer implements StreamSerializer {


    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                switch (sectionType) {
                    case PERSONAL:
                        writeTextSection(sectionType, section, dos);
                        break;
                    case OBJECTIVE:
                        writeTextSection(sectionType, section, dos);
                        break;
                    case ACHIEVEMENT:
                        writeListSection(sectionType, section, dos);
                        break;
                    case QUALIFICATIONS:
                        writeListSection(sectionType, section, dos);
                        break;
                    case EXPERIENCE:
                        writeOrganizationSection(sectionType, section, dos);
                        break;
                    case EDUCATION:
                        writeOrganizationSection(sectionType, section, dos);
                        break;
                    default:
                        break;
                }

            }

        }
    }


    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sType, readSection(dis, sType));
            }
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        Section section;
        List<String> listSection;
        List<Organization> orgList;
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(dis.readUTF());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                listSection = getListString(dis);
                section = new ListSection(listSection);
                break;
            case EXPERIENCE:
            case EDUCATION:
                orgList = getListOrganization(dis);
                section = new OrganizationSection(orgList);
                break;
            default:
                throw new IllegalStateException();
        }
        return section;

    }

    private List<String> getListString(DataInputStream dis) throws IOException {
        int sizeList = dis.readInt();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < sizeList; i++) {
            list.add(dis.readUTF());
        }
        return list;
    }

    private List<Organization> getListOrganization(DataInputStream dis) throws IOException {
        List<Organization> list = new ArrayList<Organization>();
        int sizeList = dis.readInt();
        for (int i = 0; i < sizeList; i++) {
            list.add(new Organization(dis.readUTF(), dis.readUTF(), getListPosition(dis)));
        }
        return list;
    }

    private List<Organization.Position> getListPosition(DataInputStream dis) throws IOException {
        List<Organization.Position> list = new ArrayList<Organization.Position>();
        int sizeList = dis.readInt();
        for (int i = 0; i < sizeList; i++) {
            list.add(new Organization.Position(LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF()));
        }
        return list;
    }


    private void writeTextSection(SectionType sectionType, Section section, DataOutputStream dos) throws IOException {
        dos.writeUTF(sectionType.name());
        dos.writeUTF(((TextSection) section).getText());
    }

    private void writeListSection(SectionType sectionType, Section section, DataOutputStream dos) throws IOException {
        dos.writeUTF(sectionType.name());
        List<String> list = ((ListSection) section).getListSection();
        dos.writeInt(list.size());
        for (String text : list) {
            dos.writeUTF(text);
        }
    }

    private void writeOrganizationSection(SectionType sectionType, Section section, DataOutputStream dos) throws IOException {
        dos.writeUTF(sectionType.name());
        List<Organization> listOrganization = ((OrganizationSection) section).getOrgList();
        dos.writeInt(listOrganization.size());
        for (Organization org : listOrganization) {
            dos.writeUTF(org.getOrgLink().getLinkName());
            dos.writeUTF(org.getOrgLink().getUrlName());
            List<Organization.Position> listPositions = org.getPositions();
            dos.writeInt(listPositions.size());
            for (Organization.Position pos : listPositions) {
                dos.writeUTF(pos.getStartDate().toString());
                dos.writeUTF(pos.getEndDate().toString());
                dos.writeUTF(pos.getTitle());
                dos.writeUTF(pos.getDescription());
            }
        }
    }
}


