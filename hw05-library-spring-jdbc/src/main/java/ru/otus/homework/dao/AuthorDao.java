package ru.otus.homework.dao;

import org.springframework.dao.DataAccessException;
import ru.otus.homework.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
    List<Author> collectingAuthors(String authors, int withInsert);
}
