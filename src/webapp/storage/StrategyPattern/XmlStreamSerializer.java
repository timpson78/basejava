package webapp.storage.StrategyPattern;

import webapp.model.*;
import webapp.utils.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializer{
    private  XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class,ListSection.class,Organization.Position.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (   Writer w = new OutputStreamWriter  (os, StandardCharsets.UTF_8)) {
            xmlParser.marshal(r,w);
        }
    }
    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader (is,StandardCharsets.UTF_8)) {
            return (Resume) xmlParser.unmarshall(r);
        }
    }
}


