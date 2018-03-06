package webapp.storage;

import webapp.storage.StrategyPattern.JsonStreamSerializer;


public class JsonPathStorageTest extends AbstractStorageTest{

    public JsonPathStorageTest() {
       super (new PathStorage(STORAGE_DIR.getAbsolutePath(),new JsonStreamSerializer()));
    }
}
