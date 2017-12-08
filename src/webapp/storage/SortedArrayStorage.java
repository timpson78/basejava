package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, sizeof, searchKey);
    }

    protected void fillDeleteElement(int pos) {
        int numMoved = sizeof - pos - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, pos + 1, storage, pos, numMoved);
        }
    }

    @Override
    protected void insertElement(int pos, Resume r) {
        int indexPos = -pos - 1;
        System.arraycopy(storage, indexPos, storage, indexPos + 1, sizeof - indexPos);
        storage[indexPos] = r;
    }

}
