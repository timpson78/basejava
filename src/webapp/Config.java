package webapp;

import webapp.storage.SqlStorage;
import webapp.storage.Storage;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File PROPS=new File("E:\\java\\resume\\basejava\\config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private  final Properties props = new Properties();
    private  final File storageDir;
    private  final String dbUrl;
    private  final String dbUser;
    private  final String dbPassword;


    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {

        try(InputStream is = new FileInputStream(PROPS)){
            props.load(is);
            storageDir= new File(props.getProperty("storage.dir"));
            dbUrl= props.getProperty("db.url");
            dbUser=props.getProperty("db.user");
            dbPassword=props.getProperty("db.password");
        } catch (IOException e) {
          throw new IllegalStateException("Invalid config file "+ PROPS.getAbsolutePath(),e) ;
        }
    }

    public SqlStorage getSqlStorage() {
       return new SqlStorage(dbUrl,dbUser,dbPassword);
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public File getStorageDir() {
        return storageDir;
    }
}
