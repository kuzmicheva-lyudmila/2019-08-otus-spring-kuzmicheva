package ru.otus.homework.service;

import ru.otus.homework.model.Author;
import ru.otus.homework.model.Genre;

import java.util.List;

public interface DictionaryService {
    List<Author> getAuthorsByFullname(String authors);
    void showAuthors(CommunicationService communicationService);

    List<Genre> getBookGenres();
    void showBookGenres(CommunicationService communicationService);
}
