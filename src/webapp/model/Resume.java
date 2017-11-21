/**
 * com.urise.webapp.model.Resume class
 */
package webapp.model;

public class Resume implements Comparable<Resume> {

    // Unique identifier
    public String uuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }


    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {

        return uuid;

    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return this.uuid.compareTo(o.uuid);
    }
}
