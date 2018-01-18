package webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private final List<String> listSection;


    public ListSection(List<String> listSection) {
        Objects.requireNonNull(listSection, "Lists's section must be not NULL");
        this.listSection = listSection;
    }

    public List<String> getListSection() {
        return listSection;
    }

    @Override
    public String toString() {
        return listSection.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(listSection, that.listSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listSection);
    }

}
