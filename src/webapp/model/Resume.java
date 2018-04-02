/**
 * com.urise.webapp.model.Resume class
 */
package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID=1L;
    private  String uuid;
    private  String fullName;

    public Map<ContactType, String> contacts = new EnumMap<ContactType, String>(ContactType.class);
    public Map<SectionType, Section> sections = new EnumMap<SectionType, Section>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullname) {
        this(UUID.randomUUID().toString(), fullname);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullname must not be null");
        this.uuid = uuid;
        this.fullName = fullName;

    }

    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public void setSections(Map<SectionType, Section> sections) {
        this.sections = sections;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }
    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public String getContact(ContactType cnType) {
        return contacts.get(cnType);
    }

    public Section getSection(SectionType snType) {
        return sections.get(snType);
    }


    public void addContact(ContactType cType, String cValue) {
        contacts.put(cType, cValue);
    }

    public void addSection(SectionType sType, Section SectionObjects) {
        sections.put(sType, SectionObjects);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid + "-" + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = this.fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }



    public String getFullName() {
        return fullName;
    }

}
