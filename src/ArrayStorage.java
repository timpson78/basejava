import javax.lang.model.type.NullType;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int counter;
    private int  sizeof;


    void ArrayStorage() {

        counter=0;
        sizeof=0;
    }

    void clear() {

    }

    void save(Resume r) {

        storage[sizeof]=r;
        sizeof=++sizeof;
    }

  Resume get(String uuid) {



        return null;
    }

    void delete(String uuid) {

        for (int i=0;i>sizeof;i++)
        {
            if (uuid.equals(storage[i].uuid)) {

                storage[i]=storage[sizeof];
                sizeof=--sizeof;
                break;

            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
