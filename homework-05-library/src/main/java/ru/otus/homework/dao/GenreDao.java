package ru.otus.homework.dao;

import ru.otus.homework.model.BookGenre;

import java.util.List;

public interface GenreDao {
    List<BookGenre> getAll();
}
