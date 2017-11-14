import javax.lang.model.type.NullType;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int  sizeall=10000;
    private int  sizeof;
    Resume[] storage = new Resume[sizeall];

    public void ArrayStorage() {
        sizeof=0;
    }

    public void clear() {
        sizeof=0;
        for (int i=0;i<sizeall;i++){
            storage[i]=null;
        }
    }

    public void update(Resume r) {
        int i=getPos(r);
        if (i!=-1) {
            storage[i]=r;
        } else {
            System.out.println("Элемент "+ r.uuid+" не существует");
        }
    }



    public void save(Resume r) {
        if (sizeof<=sizeall ) {
            int i = getPos(r);
            if (i == -1) {
                storage[sizeof] = r;
                sizeof = ++sizeof;
            } else {
                System.out.println("Элемент " + r.uuid + "существует");
            }
        }
    }

    public int getPos(Resume r) {
        for (int i=0;i<sizeof;i++){
            if (r.uuid.equals(storage[i].uuid)){
                return i;
            }
        }
        return -1;
    }


    public Resume get(String uuid) {
        for (int i=0;i<sizeof;i++){
            if (uuid.equals(storage[i].uuid)){
                return storage[i];
            }
        }
        return null;
    }


    public void delete(String uuid) {

        for (int i=0;i<sizeof;i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i]=storage[sizeof-1];
                storage[sizeof-1]=null;
                sizeof=--sizeof;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        Resume[] mass = new Resume[sizeof];

        for (int i=0;i<sizeof;i++) {

            mass[i]=storage[i];
        }
    return mass;
    }

   public  int size() {
        return sizeof;
    }
}
