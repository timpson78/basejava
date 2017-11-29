package webapp.exeption;

public class NotExistStorageExeption extends StorageExeption{

    public NotExistStorageExeption(String uuid) {
        super("Элемент " + uuid + " не существует",uuid);
    }
}
