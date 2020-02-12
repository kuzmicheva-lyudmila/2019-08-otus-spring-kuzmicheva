package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookInfoDao;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.BookGenre;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService{
    private final BookInfoDao bookInfoDao;
    private final DictionaryService dictionaryService;

    public BookInfoServiceImpl(BookInfoDao bookInfoDao, DictionaryService dictionaryService) {
        this.bookInfoDao = bookInfoDao;
        this.dictionaryService = dictionaryService;
    }

    @Override
    public void insertBook(CommunicationService communicationService) {
        String title = communicationService.getUserInputString("Введите наименование книги", "Некорректное наименование! Введите наименование еще раз", "[^.]+");

        String authors = communicationService.getUserInputString("Введите авторов книги (разделитель ';')", "Некорректный автор! Введите авторов еще раз", "[^.]+");
        List<Author> authorList = dictionaryService.getAuthorsByFullname(authors);

        BookGenre genre = communicationService.getUserInputString("Введите жанр книги", "Некорректный жанр книги! Введите жанр еще раз", dictionaryService.getBookGenres());

        String description = communicationService.getUserInputString("Введите описание книги", "Некорректное описание! Введите описание еще раз", "[^.]+");

        String message = null;
        try {
            Book newBook = bookInfoDao.insertBook(new Book(-1, title, authorList, genre, description));
            message = "inserted: " + newBook.toString();
        } catch (Exception e) {
            message = e.getMessage();
        }
        communicationService.showMessage(message);
    }

    @Override
    public void updateTitleBookById(CommunicationService communicationService) {
        long id = Long.parseLong(communicationService.getUserInputString("Введите идентификатор книги", "Некорректный идентификатор! Введите еще раз", "[^d]+"));
        String title = communicationService.getUserInputString("Введите наименование книги", "Некорректное наименование! Введите еще раз", "[^.]+");

        String message = null;
        try {
            Book updatedBook = bookInfoDao.updateTitleBookById(id, title);
            message = "updated: " + updatedBook.toString();
        } catch (Exception e) {
            message = e.getMessage();
        }
        communicationService.showMessage(message);
    }

    @Override
    public void deleteBookById(CommunicationService communicationService) {
        long id = Long.parseLong(communicationService.getUserInputString("Введите идентификатор книги", "Некорректный идентификатор! Введите еще раз", "[^d]+"));

        String message = null;
        try {
            long deletedBook = bookInfoDao.deleteBookById(id);
            if (deletedBook > 0) {
                message = "deleted id = " + id;
            } else {
                message = "nothing to delete";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        communicationService.showMessage(message);
    }

    @Override
    public void getAllBooks(CommunicationService communicationService) {
        List<Book> bookList = bookInfoDao.getAllBooks();
        bookList.stream().forEach(book -> communicationService.showMessage(book.toString()));
    }
}
