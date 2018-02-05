package webapp; /**
 * Test for com.urise.webapp.storage.ArrayStorage
 */

import webapp.exeption.NotExistStorageExeption;
import webapp.model.Resume;
import webapp.storage.*;
import webapp.storage.StrategyPattern.ObjectStreamSerializator;

import java.io.File;

public class MainTestArrayStorage {
    protected static final File STORAGE_DIR=new File ("E:\\java\\resume\\basejava\\storage");
    static final Storage ARRAY_STORAGE = new FileStorage(STORAGE_DIR, new ObjectStreamSerializator());
   static final Storage SORTED_ARRAY_STORAGE = new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializator());

    public static void main(String[] args) {
        Resume r1 = new Resume("Б");

        Resume r2 = new Resume("А");

        Resume r3 = new Resume("В");

        Resume r4 = new Resume("Г");

        Resume r5 = new Resume("Я");

        Resume r6 = new Resume("Е");


        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

         //SORTED_ARRAY_STORAGE.save(r2);
        // SORTED_ARRAY_STORAGE.save(r1);
        // SORTED_ARRAY_STORAGE.save(r3);
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
       /* ARRAY_STORAGE.delete(r2.getUuid());

        SORTED_ARRAY_STORAGE.delete(r2.getUuid());

        ARRAY_STORAGE.update(r3);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        StringBuffer sb = new StringBuffer();*/
    }

    static void printAll() {
        System.out.println("\nGet All");
      for ( Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
            System.out.println(r.getFullName());
        }
      /* List myLst=ARRAY_STORAGE.getAllSorted();
        System.out.println(myLst);*/


    }
}
