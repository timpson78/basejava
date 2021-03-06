package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    private Map<String, Resume> storageMap = new HashMap<>();


    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doDelete(String searchKey) {
        storageMap.remove(searchKey);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storageMap.containsKey(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        storageMap.put(searchKey, r);
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        storageMap.put(searchKey, r);
    }

    @Override
    protected Resume doGet(String searchKey) {
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