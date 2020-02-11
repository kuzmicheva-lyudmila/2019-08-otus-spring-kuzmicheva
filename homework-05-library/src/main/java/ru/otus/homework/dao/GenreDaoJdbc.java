package ru.otus.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;
    private final List<Genre> genres;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
        genres = getAllFromRepository();
    }

    @Override
    public List<Genre> getAll() {
        return genres;
    }

    private List<Genre> getAllFromRepository() {
        return jdbc.query("select * from book_genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String genre = rs.getString("genre");
            return new Genre(id, genre);
        }
    }
}
