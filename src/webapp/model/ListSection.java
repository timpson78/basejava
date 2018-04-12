package webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long serialVersionUID=1L;
    private  List<String> listSection;

    public ListSection() {
    }


    public ListSection(List<String> listSection) {
        Objects.requireNonNull(listSection, "Lists's section must be not NULL");
        this.listSection = listSection;
    }

    public ListSection(String... listSection) {
        this.listSection = Arrays.asList(listSection);
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

    public String getAllString (String separator){
        String allStrings="";
        for ( String text: listSection) {
            allStrings=allStrings+text+separator;
        }
        return allStrings;
    }

}
