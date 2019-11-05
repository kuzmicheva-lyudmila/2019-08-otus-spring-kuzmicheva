package ru.otus.homework.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Author {
    private final long id;
    private final String fullName;
    private final String description;

    public String show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Author {");
        stringBuilder.append("id = ");
        stringBuilder.append(id);
        stringBuilder.append(", fullname = ");
        stringBuilder.append(fullName);
        stringBuilder.append(", description = ");
        stringBuilder.append(description);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(fullName, author.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}
