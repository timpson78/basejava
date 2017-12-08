package webapp.storage;

import webapp.exeption.ExistStorageExeption;
import webapp.exeption.NotExistStorageExeption;
import webapp.exeption.StackOverFlowExeption;
import webapp.model.Resume;

import static webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    public Resume get(String uuid) {

        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageExeption(uuid);
            // return null;
        } else {
           return doGet(searchKey);
        }
    }

    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            doUpdate(r, searchKey);
        } else {
            throw new NotExistStorageExeption(r.getUuid());
        }
    }


    public void save(Resume r) {

        Object searchKey = getSearchKey(r.getUuid());// int pos = getPos(r.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageExeption(r.getUuid());
        } else {
            doSave(r, searchKey);
        }

    }

    public void delete(String uuid) {

        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            doDelete(searchKey);
        } else {
            throw new NotExistStorageExeption(uuid);//System.out.println("Элемент " + uuid + "не существует");
        }
    }




    /**
     * @return array, contains only Resumes in storage (without null)
     */

}
