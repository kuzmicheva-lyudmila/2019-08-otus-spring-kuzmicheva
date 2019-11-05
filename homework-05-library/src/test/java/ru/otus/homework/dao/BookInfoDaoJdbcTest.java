package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.BookGenre;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с книгами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({BookInfoDaoJdbc.class, AuthorDaoJdbc.class})
class BookInfoDaoJdbcTest {

    private static final String UPDATED_TITLE_BOOK = "Updated book";
    private static final int DELETED_ROWS = 1;
    private static final int EXPECTED_BOOKS_COUNT = 1;

    @Autowired
    private BookInfoDaoJdbc bookInfoDaoJdbc;

    @Test
    void insertBook() {
        Book newBook = bookInfoDaoJdbc.insertBook(new Book(
                -1,
                "Book_test",
                Collections.singletonList(new Author(1, "author1", "")),
                new BookGenre(1, "romance"),
                "")
        );

        assertThat(newBook.getId()).isGreaterThan(0);
    }

    @Test
    void updateTitleBookById() {
        Book updatedBook = bookInfoDaoJdbc.updateTitleBookById(1, UPDATED_TITLE_BOOK);

        assertThat(updatedBook.getTitle()).isEqualTo(UPDATED_TITLE_BOOK);
    }

    @Test
    void deleteBookById() {
        int deletedRows = bookInfoDaoJdbc.deleteBookById(1);

        assertThat(deletedRows).isEqualTo(DELETED_ROWS);
    }

    @Test
    void getAllBooks() {
        assertThat(bookInfoDaoJdbc.getAllBooks().size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }
}