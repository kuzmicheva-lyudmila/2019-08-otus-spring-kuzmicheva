package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.BookGenre;

import java.util.Collections;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public DictionaryServiceImpl(AuthorDao authorDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public List<Author> getAuthorsByFullname(String authors) {
        authorDao.insertNewAuthorsByList(authors);
        return authorDao.getExistingAuthorsByList(authors);
    }

    @Override
    public void showAuthors(CommunicationService communicationService) {
        List<Author> authorList = authorDao.getAll();
        authorList.stream().forEach(author -> communicationService.showMessage(author.show()));
    }

    @Override
    public List<BookGenre> getBookGenres() {
        return genreDao.getAll();
    }

    @Override
    public void showBookGenres(CommunicationService communicationService) {
        List<BookGenre> bookGenres = genreDao.getAll();
        bookGenres.stream().forEach(bookGenre -> communicationService.showMessage(bookGenre.show()));
    }
}
