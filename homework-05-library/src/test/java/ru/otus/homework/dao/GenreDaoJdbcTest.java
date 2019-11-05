package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO для работы с жанрами книг")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 4;
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("должен возвращать ожидаемой количество жанров книг")
    @Test
    void getAll() {
        assertThat(genreDaoJdbc.getAll().size()).isEqualTo(EXPECTED_GENRES_COUNT);
    }
}