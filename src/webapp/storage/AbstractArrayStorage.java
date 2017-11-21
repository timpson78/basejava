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
            if (i == -1) {
                System.out.println("Элемент " + uuid + " не существует");
                return null;
            } else {
                return storage[i];
            }
    }


    protected abstract int getPos(String uuid);

}
