package ru.otus.homework.dao;

import org.springframework.boot.autoconfigure.jdbc.NamedParameterJdbcOperationsDependsOnPostProcessor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.model.BookGenre;

import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations

    public GenreDaoJdbc() {

    }

    @Override
    public List<BookGenre> getAll() {

        return null;
    }
}
