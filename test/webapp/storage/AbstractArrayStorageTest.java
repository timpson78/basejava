package webapp.storage;


import org.junit.Assert;
import org.junit.Test;
import webapp.exeption.*;
import webapp.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {


    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StackOverFlowExeption.class)
    public void getStackOverflow() throws Exception {
        int initialSize = super.storage.size();
        try {
            for (int i = initialSize; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                Resume resumeNew = new Resume("uuid_test" + i);
                super.storage.save(resumeNew);
            }
        } catch (StackOverFlowExeption e) {
            Assert.fail();
        }
        super.storage.save(new Resume("get stackOverFlowExeption"));//get stackOverFlowExeption
    }

}
