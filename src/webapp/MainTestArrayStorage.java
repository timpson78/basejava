/**
 * Test for com.urise.webapp.storage.ArrayStorage
 */

import webapp.exeption.NotExistStorageExeption;
import webapp.exeption.StorageExeption;
import webapp.model.Resume;
import webapp.storage.*;

public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ListStorage();
    static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        //r1.uuid = "uuid1";
        Resume r2 = new Resume();
        //r2.uuid = "uuid2";
        Resume r3 = new Resume();
        //r3.uuid = "uuid3";

        Resume r4 = new Resume();
        //r4.uuid = "1uuid";
        Resume r5 = new Resume();
        //r5.uuid = "2uuid";
        Resume r6 = new Resume();
        //r6.uuid = "3uuid";

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r4);
        SORTED_ARRAY_STORAGE.save(r5);
        SORTED_ARRAY_STORAGE.save(r6);


        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());


        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (NotExistStorageExeption e) {

            System.out.println(e.getLocalizedMessage());
        }


        printAll();
        ARRAY_STORAGE.delete(r2.getUuid());

        SORTED_ARRAY_STORAGE.delete(r2.getUuid());

        ARRAY_STORAGE.update(r3);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        StringBuffer sb = new StringBuffer();
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
