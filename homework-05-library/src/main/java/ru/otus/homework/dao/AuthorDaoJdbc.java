package ru.otus.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public List<Author> getExistingAuthorsByList(String authors) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("p$fullname", authors)
                .addValue("p$Authors", authors);
        List<Author> existedAuthors = jdbc.query("select * from authors where position(';'||fullname||';' in ';'||:p$Authors||';') > 0", params, new AuthorMapper());
        return existedAuthors;
    }

    @Override
    public long insertNewAuthor(String fullname) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource paramsForInsert = new MapSqlParameterSource().addValue("p$fullname", fullname);
        jdbc.update("insert into authors(fullname) values (:p$fullname)", paramsForInsert, keyHolder, new String[] {"id"});
        return (long)keyHolder.getKey();
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String fullname = rs.getString("fullname");
            String description = rs.getString("description");
            return new Author(id, fullname, description);
        }
    }
}
