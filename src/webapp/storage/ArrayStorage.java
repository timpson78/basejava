package webapp.storage;

/**
 * Array based storage for Resumes
 */

import webapp.model.Resume;

import java.util.Arrays;


public class ArrayStorage extends AbstractArrayStorage {


    public void ArrayStorage() {
        sizeof = 0;
    }

    public void clear() {

        Arrays.fill(storage, 0, sizeof, null);
        sizeof = 0;

    }

    public void update(Resume r) {

        int i = getPos(r.uuid);
        if (i != -1) {
            storage[i] = r;
        } else {
            System.out.println("Элемент " + r.uuid + " не существует");
        }
    }


    public void save(Resume r) {
        if (sizeof <= STORAGE_LIMIT) {
            if (getPos(r.uuid) == -1) {
                storage[sizeof++] = r;
            } else {
                System.out.println("Элемент " + r.uuid + "существует");
            }
        }
    }

    protected int getPos(String uuid) {

        for (int i = 0; i < sizeof; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }


    public void delete(String uuid) {

        for (int i = 0; i < sizeof; i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = storage[sizeof - 1];
                storage[sizeof - 1] = null;
                sizeof--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, sizeof);
    }


}
