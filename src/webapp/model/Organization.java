package webapp.model;

import webapp.utils.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private Link orgLink;
    private List<Position> positions = new ArrayList<>();

    public Organization() {
    }

    public Organization(Link orgLink, List<Position> positions) {
        Objects.requireNonNull(orgLink.getUrlName());
        if (orgLink == null) {
            orgLink = new Link("", "");
        }
        this.orgLink = orgLink;
        this.positions = positions;
    }

    public Organization(String linkName, String urlName, Position... positions) {
        this(new Link(linkName, urlName), Arrays.asList(positions));
    }

    public Organization(String linkName, String urlName, List<Position> list) {
        this(new Link(linkName, urlName), list);
    }

    public Link getOrgLink() {
        return orgLink;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return Objects.equals(orgLink, that.orgLink) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgLink, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                orgLink.getLinkName() +
                "  " + positions +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {

            Objects.requireNonNull(startDate, "startDate must not be NULL");
            Objects.requireNonNull(endDate, "endDate must not be NULL");
            Objects.requireNonNull(title, "NameOrg must not be NULL");
            if (description == null) {
                description = "";
            }
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getDescription() {
            return description;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(endDate, position.endDate) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "Position{" + startDate + ", " + endDate + ", " + title + ", " + description + "}";
        }
    }

}
