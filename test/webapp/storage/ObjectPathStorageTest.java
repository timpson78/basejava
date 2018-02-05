package webapp.storage;

import webapp.storage.StrategyPattern.ObjectStreamSerializator;

public class ObjectPathStorageTest extends AbstractStorageTest{

    public ObjectPathStorageTest() {
       super (new PathStorage(STORAGE_DIR.getAbsolutePath(),new ObjectStreamSerializator()));
    }
}
