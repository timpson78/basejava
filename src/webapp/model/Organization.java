package webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link orgLink;
    private List<Position> positions = new ArrayList<>();


    public Organization(Link orgLink, List<Position> positions) {
        this.orgLink = orgLink;
        this.positions = positions;
    }

    public Organization(String linkName, String urlName, Position... positions) {
        /*this.orgLink = new Link(linkName, urlName);
        this.positions = Arrays.asList(positions);*/
        this(new Link(linkName, urlName), Arrays.asList(positions));
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

    public static class Position {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be NULL");
            Objects.requireNonNull(endDate, "endDate must not be NULL");
            Objects.requireNonNull(title, "NameOrg must not be NULL");
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
