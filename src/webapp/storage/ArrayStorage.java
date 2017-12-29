package webapp.storage;

/**
 * Array based storage for Resumes
 */

import webapp.model.Resume;


public class ArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid) {

        for (int i = 0; i < sizeof; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }


    protected void insertElement(int pos, Resume r) {
        storage[sizeof] = r;
    }

    protected void fillDeleteElement(int pos) {
        storage[pos] = storage[sizeof - 1];
    }
}
