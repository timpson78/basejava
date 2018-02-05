package webapp.storage.StrategyPattern;

import webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FilePathSerialization {

     void doWrite(Resume r, OutputStream file) throws IOException;

     Resume doRead(InputStream file) throws IOException;

}
