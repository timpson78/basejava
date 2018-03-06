package webapp.storage;

import webapp.exeption.StorageExeption;
import webapp.model.Resume;
import webapp.storage.StrategyPattern.StreamSerializer;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private StreamSerializer fpSerialization;

    public FileStorage(File directory, StreamSerializer fpSerialization) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(fpSerialization, "fpSerialization must not be null");
        this.fpSerialization = fpSerialization;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory ");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readble/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageExeption("file delete error", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            fpSerialization.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageExeption("Save file for Update error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            fpSerialization.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageExeption("Save file error", file.getName(), e);
        }
    }


    @Override
    protected Resume doGet(File file) {
        try {
            return fpSerialization.doRead(new BufferedInputStream(new FileInputStream(file)));

        } catch (IOException e) {
            throw new StorageExeption("File read error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doCopyStorage() {
        File[] listfiles = directory.listFiles();
        List<Resume> resumeList = new ArrayList<>();
        if (listfiles != null) {
            for (File resumefile : listfiles) {
                resumeList.add(doGet(resumefile));
            }
        } else {
            throw new StorageExeption("Read error", null);
        }
        return resumeList;
    }


    @Override
    public void clear() {
        File[] listfiles = directory.listFiles();
        if (listfiles != null) {
            for (File resumefile : listfiles) {
                doDelete(resumefile);
            }
        }

    }

    @Override
    public int size() {
        String[] listfiles = directory.list();
        if (listfiles == null) {
            throw new StorageExeption("files read error", null);
        }
        return listfiles.length;
    }
}
