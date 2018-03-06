package webapp.storage;

import webapp.storage.StrategyPattern.ObjectStreamSerializator;
import webapp.storage.StrategyPattern.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest{

    public XmlPathStorageTest() {
       super (new PathStorage(STORAGE_DIR.getAbsolutePath(),new XmlStreamSerializer()));
    }
}
