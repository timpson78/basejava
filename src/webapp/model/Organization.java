package webapp.model;

import java.util.Date;
import java.util.Objects;

public class Organization {
    private final Link orgLink;
    private final Date startDate;
    private final Date endDate;
    private final String nameOrg;
    private final String description;

    public Organization(String linkName, String urlName, Date startDate, Date endDate, String nameOrg, String description) {

        Objects.requireNonNull(linkName, "linkName must not be NULL");
        Objects.requireNonNull(urlName, "urlName must not be NULL");
        Objects.requireNonNull(startDate, "startDate must not be NULL");
        Objects.requireNonNull(endDate, "endDate must not be NULL");
        Objects.requireNonNull(nameOrg, "NameOrg must not be NULL");
        this.orgLink = new Link(linkName, urlName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.nameOrg = nameOrg;
        this.description = description;
    }

    public Link getOrgLink() {
        return orgLink;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(orgLink, that.orgLink) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(nameOrg, that.nameOrg) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgLink, startDate, endDate, nameOrg, description);
    }
}
