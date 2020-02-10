package ru.otus.homework.model;

import lombok.Data;

@Data
public class BookGenre extends CommunicationDaoModel{
    private final long id;
    private final String genre;

    public String show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Book {");
        stringBuilder.append("id = ");
        stringBuilder.append(id);
        stringBuilder.append(", genre = ");
        stringBuilder.append(genre);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equalsByString(String parameter) {
        return genre.equals(parameter);
    }
}
