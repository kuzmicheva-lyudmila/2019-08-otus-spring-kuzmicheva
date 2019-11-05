package ru.otus.homework.dao;

import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с авторами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final Object COLLECTING_AUTHORS_EXPECTED_COUNT = 2;
    private static final Object INSERTING_AUTHORS_EXPECTED_COUNT = 1;

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void getAll() {
        List<Author> authorList = authorDaoJdbc.getAll();
        assertThat(authorList.size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @Test
    void getExistingAuthorsByList() {
        assertThat(authorDaoJdbc.getExistingAuthorsByList("author1;author2;author3").size()).isEqualTo(COLLECTING_AUTHORS_EXPECTED_COUNT);
    }

    @Test
    void insertNewAuthorsByList() {
        assertThat(authorDaoJdbc.insertNewAuthorsByList("author1;author2;author3").size()).isEqualTo(INSERTING_AUTHORS_EXPECTED_COUNT);
    }
}