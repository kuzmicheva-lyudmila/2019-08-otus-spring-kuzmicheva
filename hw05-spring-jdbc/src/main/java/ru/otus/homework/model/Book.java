package ru.otus.homework.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private final long id;
    private final List<Author> authors;
    private final BookGenre genre;
    private final String title;
    private final String description;
}
