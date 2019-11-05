package ru.otus.homework.dao;

import org.springframework.dao.DataAccessException;
import ru.otus.homework.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
    List<Author> getExistingAuthorsByList(String authors);
    List<Author> insertNewAuthorsByList(String authors);
}
