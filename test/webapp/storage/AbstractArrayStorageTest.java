package webapp.storage;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exeption.*;
import webapp.model.Resume;

import javax.accessibility.Accessible;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID[] = {"uuid1", "uuid2", "uuid3"};


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        for (int i = 0; i < UUID.length; i++) {
            storage.save(new Resume(UUID[i]));
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageExeption.class)
    public void get() throws Exception {
        for (int i = 0; i < storage.size(); i++) {
            Assert.assertEquals(UUID[i], storage.get(UUID[i]).getUuid());
        }
        storage.get("uuid_Not_Exist");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageExeption.class)
    public void update() throws Exception {
        Resume resumeForUpdate = new Resume(UUID[0]);
        storage.update(resumeForUpdate);
        Assert.assertTrue(resumeForUpdate == storage.get(resumeForUpdate.getUuid()));
        storage.update(new Resume("uuid_Not_exist"));//get NotExistStorageExeption
    }

    @Test(expected = ExistStorageExeption.class)
    public void save() throws Exception {
        Resume storageNew = new Resume("uuid4");
        storage.save(storageNew);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(storageNew.getUuid(), storage.get("uuid4").getUuid());

        storage.save(new Resume(UUID[0])); //get ExistStorageExeption
    }

    @Test(expected = NotExistStorageExeption.class)
    public void delete() throws Exception {
        storage.delete("uuid2");
        Assert.assertEquals(2, storage.size());
        storage.get("uuid2");
        storage.delete("uuid_Not_Exist"); //get NotExistStorageExeption
    }

    @Test
    public void getAll() throws Exception {
        Resume[] ResumeData = storage.getAll();
        Assert.assertEquals(UUID.length, ResumeData.length);
        for (int i = 0; i < ResumeData.length; i++) {
            Assert.assertEquals(UUID[i], ResumeData[i].getUuid());
        }
    }

    @Test(expected = NotExistStorageExeption.class)
    public void getNotExist() throws Exception {
        storage.get("dummy"); //get
    }

    @Test(expected = StackOverFlowExeption.class)
    public void getStackOverflow() throws Exception {
        int initialSize = storage.size();
        try {
            for (int i = initialSize; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                Resume resumeNew = new Resume("uuid_test" + i);
                storage.save(resumeNew);
            }
        } catch (StackOverFlowExeption e) {
            Assert.fail();
        }
        storage.save(new Resume());//get stackOverFlowExeption
    }

}
