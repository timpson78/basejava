package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

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
    protected void doDelete(Integer index) {
        storageLst.remove((int) index);
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != null;
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storageLst.set((Integer) index, r);
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        storageLst.add(r);
    }

    @Override
    protected Resume doGet(Integer index) {
        return storageLst.get(index);
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

