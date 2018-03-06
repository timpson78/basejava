package webapp.storage.StrategyPattern;

import webapp.exeption.StorageExeption;
import webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class  DataStreamSerializer implements StreamSerializer {


    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType,String> entry: contacts.entrySet()){
               dos.writeUTF(entry.getKey().name());
               dos.writeUTF(entry.getValue());
           }

        }
    }


    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid=dis.readUTF();
            String fulname=dis.readUTF();
            Resume resume=new Resume(uuid,fulname);
            int size=dis.readInt();
            for (int i=0;i<size;i++){
                ContactType contactType=ContactType.valueOf(dis.readUTF());
                String value=dis.readUTF();
                resume.addContact(contactType, value);
            }
            return null;
        }
    }



}


