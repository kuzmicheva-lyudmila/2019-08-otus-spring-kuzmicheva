package ru.otus.homework.dao;

import ru.otus.homework.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
    List<Author> getExistingAuthorsByList(String authors);
    long insertNewAuthor(String authors);
}
