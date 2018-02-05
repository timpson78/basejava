package webapp.storage;

import webapp.storage.StrategyPattern.ObjectStreamSerializator;

public class ObjectFileStorageTest extends AbstractStorageTest{

    public ObjectFileStorageTest() {
        super (new FileStorage(STORAGE_DIR,new ObjectStreamSerializator()));
    }
}
