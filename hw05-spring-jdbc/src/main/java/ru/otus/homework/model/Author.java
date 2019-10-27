package ru.otus.homework.model;

import lombok.Data;

@Data
public class Author {
    private final long id;
    private final String fullName;
    private final String description;
}
