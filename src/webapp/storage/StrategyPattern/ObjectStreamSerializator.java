package webapp.storage.StrategyPattern;

import webapp.exeption.StorageExeption;
import webapp.model.Resume;

import java.io.*;


public class ObjectStreamSerializator implements FilePathSerialization {

    public void doWrite(Resume r, OutputStream outStream) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(outStream)) {
            oos.writeObject(r);
        }
    }

    public Resume doRead(InputStream inStream) throws IOException {
        try (ObjectInputStream oos = new ObjectInputStream(inStream)) {
            return (Resume) oos.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageExeption("Error read resume", null, e);
        }
    }
}

