package webapp.storage;

import webapp.exeption.ExistStorageExeption;
import webapp.exeption.NotExistStorageExeption;
import webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract List<Resume> doCopyStorage();


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
            throw new NotExistStorageExeption(uuid);
        }
    }


    public List<Resume> getAllSorted() {
        List<Resume> resumeList = doCopyStorage();
        Collections.sort(resumeList);
        return resumeList;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

}
