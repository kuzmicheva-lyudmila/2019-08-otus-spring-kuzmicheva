package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Genre;

import java.util.List;
import java.util.Optional;

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
        List<Author> existedAuthors = authorDao.getExistingAuthorsByList(authors);
        boolean isInserted = false;

        for (String fullname : authors.split(";")) {
            Author comparisonAuthor = new Author(-1, fullname, "");
            Optional<Author> author = existedAuthors.stream()
                    .filter(e -> e.equals(comparisonAuthor))
                    .findAny();

            if (!author.isPresent()) {
                long id = authorDao.insertNewAuthor(fullname);
                if (id > 0 && !isInserted) {
                    isInserted = true;
                }
            }
        }

        return (isInserted) ? authorDao.getExistingAuthorsByList(authors) : existedAuthors;
    }

    @Override
    public void showAuthors(CommunicationService communicationService) {
        List<Author> authorList = authorDao.getAll();
        authorList.stream().forEach(author -> communicationService.showMessage(author.toString()));
    }

    @Override
    public List<Genre> getBookGenres() {
        return genreDao.getAll();
    }

    @Override
    public void showBookGenres(CommunicationService communicationService) {
        List<Genre> genres = genreDao.getAll();
        genres.stream().forEach(bookGenre -> communicationService.showMessage(bookGenre.toString()));
    }
}
