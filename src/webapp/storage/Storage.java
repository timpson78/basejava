package webapp.storage;

/**
 * Array based storage for Resumes
 */

import webapp.model.Resume;


public interface Storage {

    public void clear();

    public void update(Resume r);

    public void save(Resume r);

    public Resume get(String uuid);

    public void delete(String uuid);

    public Resume[] getAll();

    public int size();
}