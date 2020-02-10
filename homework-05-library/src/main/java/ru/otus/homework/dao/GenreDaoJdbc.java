package ru.otus.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.model.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;
    private final List<BookGenre> bookGenres;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
        bookGenres = getAllFromRepository();
    }

    @Override
    public List<BookGenre> getAll() {
        return bookGenres;
    }

    private List<BookGenre> getAllFromRepository() {
        return jdbc.query("select * from book_genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<BookGenre> {

        @Override
        public BookGenre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String genre = rs.getString("genre");
            return new BookGenre(id, genre);
        }
    }
}
