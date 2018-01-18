package webapp.storage;

/**
 * Array based storage for Resumes
 */

import webapp.exeption.StackOverFlowExeption;
import webapp.model.Resume;

import java.util.Arrays;
import java.util.List;


public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected int sizeof;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return sizeof;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void fillDeleteElement(int pos);

    protected abstract void insertElement(int pos, Resume r);

    protected List<Resume> doCopyStorage() {
        Resume[] tmpStorage = Arrays.copyOfRange(storage, 0, sizeof);
        return Arrays.asList(tmpStorage);
    }


    protected void doDelete(Integer index) {
        fillDeleteElement(index);
        storage[sizeof - 1] = null;
        sizeof--;
    }

    ;

    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    ;

    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    ;

    protected void doSave(Resume r, Integer index) {
        if (sizeof == STORAGE_LIMIT) {
            throw new StackOverFlowExeption();
        } else {
            insertElement(index, r);
            sizeof++;
        }
    }

    ;

    protected Resume doGet(Integer index) {
        return storage[index];
    }

    ;

    public void clear() {

        Arrays.fill(storage, 0, sizeof, null);
        sizeof = 0;

    }

    ;


}
