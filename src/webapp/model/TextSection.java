package webapp.model;

import java.util.Objects;

public class TextSection extends Section {
    private static final long serialVersionUID=1L;
    private final String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "Text of TextSection must be not  NULL");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

}

