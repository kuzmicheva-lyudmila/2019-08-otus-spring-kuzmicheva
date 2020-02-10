package ru.otus.homework.service;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.model.BookGenre;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для ввода/вывода данных")
@SpringBootTest
class CommunicationServiceImplTest {

    private static final String EXAMPLE_OF_BOOK_GENRE = "romance";

    @Configuration
    static class CommunicationServiceImplContextConfiguration {
        @Bean
        public CommunicationService communicationService(CommunicationDao communicationDao) {
            return new CommunicationServiceImpl(communicationDao);
        }
    }

    @MockBean
    CommunicationDao communicationDao;

    @Autowired
    CommunicationService communicationService;

    @Test
    void getUserInputStringWithList() {
        BookGenre bookGenre = new BookGenre(1, EXAMPLE_OF_BOOK_GENRE);
        when(communicationDao.getUserInputString(eq(EXAMPLE_OF_BOOK_GENRE), any(), anyList())).thenReturn(bookGenre);

        assertThat(communicationService.getUserInputString(EXAMPLE_OF_BOOK_GENRE, Strings.EMPTY, Collections.singletonList(bookGenre))).isEqualTo(bookGenre);
        verify(communicationDao, times(1)).getUserInputString(any(), any(), anyList());
    }

    @Test
    void getUserInputStringWithString() {
        when(communicationDao.getUserInputString(eq(EXAMPLE_OF_BOOK_GENRE), any(), anyString())).thenReturn(EXAMPLE_OF_BOOK_GENRE);

        assertThat(communicationService.getUserInputString(EXAMPLE_OF_BOOK_GENRE, Strings.EMPTY, "[^.]+)")).isEqualTo(EXAMPLE_OF_BOOK_GENRE);
        verify(communicationDao, times(1)).getUserInputString(any(), any(), anyString());
    }

    @Test
    void showMessage() {
        communicationService.showMessage(Strings.EMPTY);
        verify(communicationDao, times(1)).showMessage(any());
    }
}