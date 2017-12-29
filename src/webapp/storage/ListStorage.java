package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storageLst = new ArrayList<>();


    @Override
    protected Integer getSearchKey(String uuid) {

        for (Integer i = 0; i < storageLst.size(); i++) {
            if (uuid.equals(storageLst.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doDelete(Object index) {
        storageLst.remove((int) index);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index != null;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storageLst.set((Integer) index, r);
    }

    @Override
    protected void doSave(Resume r, Object index) {
        storageLst.add(r);
    }

    @Override
    protected Resume doGet(Object index) {
        return storageLst.get((Integer) index);
    }

    @Override
    protected List<Resume> doCopyStorage() {
        return new ArrayList(storageLst);
    }

    @Override
    public void clear() {
        storageLst.clear();
    }


    @Override
    public int size() {
        return storageLst.size();
    }
}

