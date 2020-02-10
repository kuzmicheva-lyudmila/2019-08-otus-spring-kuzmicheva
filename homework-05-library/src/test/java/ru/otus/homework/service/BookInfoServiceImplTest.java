package ru.otus.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.BookInfoDao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для работы с книгами")
@SpringBootTest
class BookInfoServiceImplTest {

    @Configuration
    static class BookInfoServiceImplContextConfiguration {
        @Bean
        public BookInfoService bookInfoService(BookInfoDao bookInfoDao, DictionaryService dictionaryService) {
            return new BookInfoServiceImpl(bookInfoDao, dictionaryService);
        }
    }

    @MockBean
    BookInfoDao bookInfoDao;

    @MockBean
    DictionaryService dictionaryService;

    @MockBean
    CommunicationService communicationService;

    @Autowired
    BookInfoService bookInfoService;

    @Test
    void insertBook() {
        bookInfoService.insertBook(communicationService);
        verify(bookInfoDao, times(1)).insertBook(any());
    }

    @Test
    void updateTitleBookById() {
        when(communicationService.getUserInputString(any(), any(), (String) any())).thenReturn(String.valueOf(1));
        bookInfoService.updateTitleBookById(communicationService);

        verify(bookInfoDao, times(1)).updateTitleBookById(anyLong(), anyString());
    }

    @Test
    void deleteBookById() {
        when(communicationService.getUserInputString(any(), any(), (String) any())).thenReturn(String.valueOf(1));
        bookInfoService.deleteBookById(communicationService);

        verify(bookInfoDao, times(1)).deleteBookById(anyLong());
    }

    @Test
    void getAllBooks() {
        bookInfoService.getAllBooks(communicationService);
        verify(bookInfoDao, times(1)).getAllBooks();
    }
}