import javax.lang.model.type.NullType;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int  sizeof;


    void ArrayStorage() {
        sizeof=0;
    }

    void clear() {
        sizeof=0;
        for (int i=0;i>storage.length;i++){
            storage[i]=null;
        }


    }

    void save(Resume r) {
        storage[sizeof]=r;
        sizeof=++sizeof;
    }

  Resume get(String uuid) {

    for (int i=0;i<sizeof;i++){
        if (uuid.equals(storage[i].uuid)){
            return storage[i];
        }
    }

        return null;
    }

    void delete(String uuid) {

        for (int i=0;i<sizeof;i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i]=storage[sizeof-1];
                sizeof=--sizeof;
                break;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Resume[] mass = new Resume[sizeof];

        for (int i=0;i<sizeof;i++) {

            mass[i]=storage[i];
        }
    return mass;
    }

    int size() {
        return sizeof;
    }
}
