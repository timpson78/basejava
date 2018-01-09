package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();


    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storageMap.remove(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storageMap.containsKey(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storageMap.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storageMap.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storageMap.get(searchKey);
    }

    @Override
    protected List<Resume> doCopyStorage() {
        return new ArrayList<Resume>(storageMap.values());
    }

    @Override
    public void clear() {
        storageMap.clear();
    }


    @Override
    public int size() {
        return storageMap.size();
    }
}