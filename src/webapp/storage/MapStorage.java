package webapp.storage;

import webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapStorage extends AbstractStorage {

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

        Resume myResume = storageMap.get(searchKey);
        return myResume != null;

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
    public void clear() {
        storageMap.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumeArray = new Resume[storageMap.size()];
        int i = 0;
        for (Map.Entry<String, Resume> entry : storageMap.entrySet()) {
            resumeArray[i] = entry.getValue();
            i++;
        }
        return resumeArray;
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
