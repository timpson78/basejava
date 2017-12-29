/**
 * com.urise.webapp.model.Resume class
 */
package webapp.model;

import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;


    public Resume(String fullname) {
        this(UUID.randomUUID().toString(),fullname);
    }

    public Resume(String uuid,String fullName) {
        Objects.requireNonNull(uuid,"uuid must not be null");
        Objects.requireNonNull(fullName,"fullname must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid+"-"+fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = this.fullName.compareTo(o.fullName);
        return cmp!=0 ? cmp:uuid.compareTo(o.uuid);
    }

    public String getFullName() {
        return fullName;
    }

}
