package webapp.storage;

import webapp.exeption.ExistStorageExeption;
import webapp.exeption.NotExistStorageExeption;
import webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    //protected final Logger LOG =Logger.getLogger(getClass().getName());
    private static final Logger LOG=Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> doCopyStorage();


    public Resume get(String uuid) {
        LOG.info("Get "+uuid);
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageExeption(uuid);
            // return null;
        } else {
            return doGet(searchKey);
        }
    }

    public void update(Resume r) {
        LOG.info("Update "+r);
        SK searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            doUpdate(r, searchKey);
        } else {
            throw new NotExistStorageExeption(r.getUuid());
        }
    }


    public void save(Resume r) {
        LOG.info("Save "+r);
        SK searchKey = getSearchKey(r.getUuid());// int pos = getPos(r.getUuid());
        if (isExist(searchKey)) {
            LOG.warning(" Element " + r.getUuid() + " is exist");
            throw new ExistStorageExeption(r.getUuid());
        } else {
            doSave(r, searchKey);
        }

    }

    public void delete(String uuid) {

        LOG.info("Delete "+uuid);

        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            doDelete(searchKey);
        } else {
            LOG.warning(" Element " + uuid + " does not exist");
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
