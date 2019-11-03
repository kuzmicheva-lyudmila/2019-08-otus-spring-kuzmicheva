package ru.otus.homework.service;

import ru.otus.homework.model.Author;
import ru.otus.homework.model.BookGenre;

import java.util.List;
import java.util.Map;

public interface DictionaryService {
    List<Author> getAuthorsByFullname(String authors);
    void showAuthors(CommunicationService communicationService);

    List<BookGenre> getBookGenres();
    void showBookGenres(CommunicationService communicationService);
}
