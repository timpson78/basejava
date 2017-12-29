package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();


    @Override
    protected Resume getSearchKey(String uuid) {

        return storageMap.get(uuid);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storageMap.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected boolean isExist(Object searchKey) {

     /*   Resume myResume = storageMap.get(searchKey);
        return myResume != null;*/
        //return storageMap.containsKey((String)searchKey);

        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storageMap.put(((Resume) searchKey).getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storageMap.get(((Resume) searchKey).getUuid());

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
