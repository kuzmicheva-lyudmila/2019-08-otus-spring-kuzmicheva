package ru.otus.homework.dao;

import ru.otus.homework.model.Book;

import java.util.List;

public interface BookInfoDao {
    Book insertBook(Book book);

    Book updateTitleBookById(long id, String title);

    int deleteBookById(long id);

    List<Book> getAllBooks();
}
