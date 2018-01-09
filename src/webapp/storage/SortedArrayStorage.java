package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {


   /* private static final class ResumeComparator implements Comparator<Resume> {
        public int compare(Resume r1, Resume r2) {
            return r1.getUuid().compareTo(r2.getUuid());
        }
    }*/

    //private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());
    // private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid,( o1, o2) -> o1.compareTo(o2));
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);
    @Override
    public Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "empty");
        return Arrays.binarySearch(storage, 0, sizeof, searchKey, RESUME_COMPARATOR );

    }

    @Override
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
