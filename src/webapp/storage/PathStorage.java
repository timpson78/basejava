package webapp.storage;

import webapp.exeption.StorageExeption;
import webapp.model.Resume;
import webapp.storage.StrategyPattern.FilePathSerialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private FilePathSerialization fpSerialization;

    public PathStorage(String directory, FilePathSerialization fpSerialization) {
        Objects.requireNonNull(fpSerialization, "directory must not be null");
        this.fpSerialization = fpSerialization;
        this.directory = Paths.get(directory);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(this.directory) || !Files.isWritable(this.directory)) {
            throw new IllegalArgumentException(directory + " is not directory or is not writable ");
        }
    }

    @Override
    protected Path getSearchKey(String filename) {
       // return Paths.get (directory.toString() + "\\" + filename);
        return directory.resolve(directory.toAbsolutePath().toString() + "\\" + filename);
    }

    @Override
    protected void doDelete(Path path) {

        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageExeption("Path: Delete errors", path.getFileName().toString(), e);
        }

    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path.toAbsolutePath());
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            fpSerialization.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageExeption("Path: Update error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            fpSerialization.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageExeption("Path: Save error", path.getFileName().toString(), e);
        }
    }


    @Override
    protected Resume doGet(Path path) {
        try {
            return fpSerialization.doRead(new BufferedInputStream(Files.newInputStream(path)));

        } catch (IOException e) {
            throw new StorageExeption("Path: Read error", path.getFileName().toString(), e);
        }

    }

    @Override
    protected List<Resume> doCopyStorage() {
        List<Resume> resumeList = new ArrayList<>();
        try {
            Files.list(directory).forEach(path -> resumeList.add(doGet(path)));
        } catch (IOException e) {
            throw new StorageExeption("Copy Error ", directory.getFileName().toString(), e);
        }

        return resumeList;
    }


    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageExeption("File delete error", directory.toString(), e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageExeption("File delete error", directory.toString(), e);
        }
    }
}
