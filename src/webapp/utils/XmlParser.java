package webapp.utils;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBException;

import java.io.Reader;
import java.io.Writer;

public class XmlParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;


    public XmlParser(Class... classesTobeBound ) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesTobeBound);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }


    public void  marshal (Object instance, Writer writer) {
        try {
            marshaller.marshal(instance,writer);
        } catch (JAXBException e) {
           throw new IllegalStateException(e);
        }
    }

    public <T> T unmarshall(Reader reader)
    {
        try {
            return (T)unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }
}
