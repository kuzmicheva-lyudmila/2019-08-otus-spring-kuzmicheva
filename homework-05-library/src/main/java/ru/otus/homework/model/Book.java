package ru.otus.homework.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private final long id;
    private final String title;
    private final List<Author> authors;
    private final BookGenre genre;
    private final String description;

    public String show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Book {");
        stringBuilder.append("id = ");
        stringBuilder.append(id);
        stringBuilder.append(", title = ");
        stringBuilder.append(title);
        stringBuilder.append(", authors = {");
        stringBuilder.append(authors.toString());
        stringBuilder.append("}, genre = ");
        stringBuilder.append(genre.toString());
        stringBuilder.append(", description = ");
        stringBuilder.append(description);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
