package webapp.exeption;

public class ExistStorageExeption extends StorageExeption{

    public ExistStorageExeption(String uuid) {
        super("Элемент " + uuid + " существует",uuid);
    }
}
