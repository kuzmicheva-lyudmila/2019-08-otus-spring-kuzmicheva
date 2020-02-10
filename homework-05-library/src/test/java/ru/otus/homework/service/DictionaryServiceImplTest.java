package ru.otus.homework.service;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.BookGenre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для работы со справочниками")
@SpringBootTest
class DictionaryServiceImplTest {

    private static final String FULLNAME_OF_AUTHOR = "Author1";
    private static final String BOOK_GENRE = "romance";

    @Configuration
    static class DictionaryServiceImplContextConfiguration {
        @Bean
        public DictionaryService dictionaryService(AuthorDao authorDao, GenreDao genreDao) {
            return new DictionaryServiceImpl(authorDao, genreDao);
        }
    }

    @MockBean
    AuthorDao authorDao;

    @MockBean
    GenreDao genreDao;

    @MockBean
    CommunicationService communicationService;

    @Autowired
    DictionaryService dictionaryService;

    @Test
    void getAuthorsByFullname() {
        List<Author> authorList = Collections.singletonList(new Author(1, FULLNAME_OF_AUTHOR, Strings.EMPTY));
        when(authorDao.getExistingAuthorsByList(eq(FULLNAME_OF_AUTHOR))).thenReturn(authorList);

        assertThat(dictionaryService.getAuthorsByFullname(FULLNAME_OF_AUTHOR)).isEqualTo(authorList);
        verify(authorDao, times(1)).getExistingAuthorsByList(anyString());
    }

    @Test
    void showAuthors() {
        dictionaryService.showAuthors(communicationService);
        verify(authorDao, times(1)).getAll();
    }

    @Test
    void getBookGenres() {
        List<BookGenre> bookGenres = Collections.singletonList(new BookGenre(1, BOOK_GENRE));
        when(genreDao.getAll()).thenReturn(bookGenres);

        assertThat(dictionaryService.getBookGenres()).isEqualTo(bookGenres);
        verify(genreDao, times(1)).getAll();
    }

    @Test
    void showBookGenres() {
        dictionaryService.showBookGenres(communicationService);
        verify(genreDao, times(1)).getAll();
    }
}