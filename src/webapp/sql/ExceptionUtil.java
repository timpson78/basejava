package webapp.sql;

import webapp.exeption.ExistStorageExeption;
import webapp.exeption.StorageExeption;

import java.sql.SQLException;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static StorageExeption ConvertExeption(SQLException e){
        if (e.getSQLState().equals("23505")){
            return new ExistStorageExeption(null);
        }
        return new StorageExeption(e);
    }

}
