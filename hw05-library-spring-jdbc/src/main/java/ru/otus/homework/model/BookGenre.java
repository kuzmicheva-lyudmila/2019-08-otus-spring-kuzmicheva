package ru.otus.homework.model;

import lombok.Data;

import java.util.Objects;

@Data
public class BookGenre extends CommunicationDaoModel{
    private final long id;
    private final String genre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenre bookGenre = (BookGenre) o;
        return genre.equals(bookGenre.genre);
    }

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

    @Override
    public boolean equalsByString(String parameter) {
        return genre.equals(parameter);
    }
}
