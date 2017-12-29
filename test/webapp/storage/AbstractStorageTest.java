package webapp.storage;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exeption.ExistStorageExeption;
import webapp.exeption.NotExistStorageExeption;
import webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID[] = {"uuid1", "uuid2", "uuid3"};


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        for (String aUUID : UUID) {
            storage.save(new Resume(aUUID, aUUID));
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        for (int i = 0; i < storage.size(); i++) {
            Assert.assertEquals(UUID[i], storage.get(UUID[i]).getUuid());
        }
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageExeption.class)
    public void update() throws Exception {
        storage.get("uuid_Not_Exist");
        Resume resumeForUpdate = new Resume(UUID[0]);
        storage.update(resumeForUpdate);
        Assert.assertTrue(resumeForUpdate == storage.get(resumeForUpdate.getUuid()));
        storage.update(new Resume("uuid_Not_exist"));//get NotExistStorageExeption
        storage.delete("uuid_Not_Exist");
    }

    @Test(expected = ExistStorageExeption.class)
    public void save() throws Exception {
        Resume storageNew = new Resume("uuid4", "Empty");
        storage.save(storageNew);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(storageNew.getUuid(), storage.get("uuid4").getUuid());
        storage.save(new Resume(UUID[0], "Empty")); //get ExistStorageExeption
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid2");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> myLst = storage.getAllSorted();
        Assert.assertEquals(UUID.length, myLst.size());
        for (int i = 0; i < myLst.size(); i++) {
            Assert.assertEquals(UUID[i], myLst.get(i).getUuid());
        }
    }


}
