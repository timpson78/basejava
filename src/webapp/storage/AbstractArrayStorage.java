package webapp.storage;

/**
 * Array based storage for Resumes
 */

import webapp.exeption.ExistStorageExeption;
import webapp.exeption.NotExistStorageExeption;
import webapp.exeption.StackOverFlowExeption;
import webapp.exeption.StorageExeption;
import webapp.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int sizeof;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return sizeof;
    }

    protected abstract Integer getSearchKey(String uuid);
    protected abstract void fillDeleteElement(int pos);
    protected abstract void insertElement(int pos, Resume r);

    protected void doDelete(Object index) {
        fillDeleteElement((Integer) index);
        storage[sizeof - 1] = null;
        sizeof--;
    }

    ;

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    ;

    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    ;

    protected void doSave(Resume r, Object index) {
        if (sizeof == STORAGE_LIMIT) {
            throw new StackOverFlowExeption();
        } else {
            insertElement((Integer) index, r);
            sizeof++;
        }
    }

    ;

    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    ;

    public void clear() {

        Arrays.fill(storage, 0, sizeof, null);
        sizeof = 0;

    }


    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, sizeof);
    }

}
