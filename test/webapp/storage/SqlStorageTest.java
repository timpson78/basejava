package webapp.storage;

import webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
      //  super(new SqlStorage(Config.getInstance().getDbUrl(),Config.getInstance().getDbUser(),Config.getInstance().getDbPassword()));
        super (Config.getInstance().getSqlStorage());
    }
}
