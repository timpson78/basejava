package webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {

    private static final long serialVersionUID=1L;
    private final List<Organization> orgList;

    public OrganizationSection(List<Organization> orgList) {
        Objects.requireNonNull(orgList, "Organization's List must not be NULL");
        this.orgList = orgList;
    }

    public OrganizationSection(Organization... orgList) {
        this.orgList = Arrays.asList(orgList);
    }

    public List<Organization> getOrgList() {
        return orgList;
    }

    @Override
    public String toString() {
        return orgList.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(orgList, that.orgList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgList);
    }
}
