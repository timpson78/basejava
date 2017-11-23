package webapp.storage;

/**
 * Array based storage for Resumes
 */

import webapp.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int sizeof;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return sizeof;
    }

    public Resume get(String uuid) {

            int i = getPos(uuid);
            if (i <0) {
                System.out.println("Элемент " + uuid + " не существует");
                return null;
            } else {
                return storage[i];
            }
    }



    public void ArrayStorage() {
        sizeof = 0;
    }

    public void clear() {

        Arrays.fill(storage, 0, sizeof, null);
        sizeof = 0;

    }

    public void update(Resume r) {

        int pos = getPos(r.uuid);
        if (pos >=0 ) {
            storage[pos] = r;
        } else {
            System.out.println("Элемент " + r.uuid + " не существует");
        }
    }


    public void save(Resume r) {
        if (sizeof <= STORAGE_LIMIT) {
            int pos = getPos(r.uuid);
            if (pos < 0) {
                insertElement(pos,r);
                sizeof++;
            } else {
                System.out.println("Элемент " + r.uuid + "существует");
            }
        }
    }


    public void delete(String uuid) {

        int pos = getPos(uuid);
        if (pos >=0) {
                fillDeleteElement(pos);
                storage[sizeof - 1] = null;
                sizeof--;
            } else {
            System.out.println("Элемент " + uuid + "не существует");
        }
    }



    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, sizeof);
    }

    protected abstract int getPos(String uuid);

    protected abstract void fillDeleteElement(int pos);

    protected abstract void insertElement(int pos,Resume r);




}
