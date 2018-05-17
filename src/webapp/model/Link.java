package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {
    private  String linkName;
    private  String urlName;

    public Link() {
    }

    public Link(String linkName, String urlName) {
        Objects.requireNonNull(linkName, "Link's name must not be NULL");
        Objects.requireNonNull(urlName, "Link's name must not be NULL");
        this.linkName = linkName;
        this.urlName = urlName;
    }

    public String   getLinkName() {
        return linkName;
    }

    public String getUrlName() {
        return urlName;
    }

    @Override
    public String toString() {
        return linkName + "-" + urlName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link = (Link) o;
        return Objects.equals(linkName, link.linkName) &&
                Objects.equals(urlName, link.urlName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkName, urlName);
    }
}
