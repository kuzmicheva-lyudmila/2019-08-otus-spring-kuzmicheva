package ru.otus.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookInfoDaoJdbc implements BookInfoDao{
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;

    public BookInfoDaoJdbc(NamedParameterJdbcOperations jdbc, AuthorDao authorDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
    }

    @Override
    public Book insertBook(Book book) {
        Book newBook = null;
        KeyHolder keyHolderForBook = new GeneratedKeyHolder();
        SqlParameterSource paramsForInsertBook = new MapSqlParameterSource().addValue("p$bookname", book.getTitle())
                    .addValue("p$genreid", book.getGenre().getId())
                    .addValue("p$description", book.getDescription());
        int inserted_rows = jdbc.update("insert into books (book_name, genre_id, description) values (:p$bookname, :p$genreid, :p$description)", paramsForInsertBook, keyHolderForBook, new String[] {"id"});

        if (inserted_rows > 0) {
            long bookId = keyHolderForBook.getKey().longValue();
            newBook = new Book(bookId, book.getTitle(), book.getAuthors(), book.getGenre(), book.getDescription());
            if (book.getAuthors().size() > 0) {
                for (Author author : book.getAuthors()) {
                    SqlParameterSource paramsForInsertAuthor = new MapSqlParameterSource().addValue("p$bookid", bookId)
                            .addValue("p$authorid", author.getId());
                   jdbc.update("insert into book_authors (book_id, author_id) values (:p$bookid, :p$authorid)", paramsForInsertAuthor);
                }
            }
        }

        return newBook;
    }

    @Override
    public Book updateTitleBookById(long id, String title) {
        Book newBook = null;

        SqlParameterSource paramsForUpdateBook = new MapSqlParameterSource().addValue("p$bookname", title)
                .addValue("p#Id", id);
        int updated_rows = jdbc.update("update books set book_name = :p$bookname where id = :p#Id", paramsForUpdateBook);

        if (updated_rows > 0) {
            newBook = getBookById(id);
        }

        return newBook;
    }

    @Override
    public int deleteBookById(long id) {
        SqlParameterSource paramsForDeleteBook = new MapSqlParameterSource().addValue("p#Id", id);
        jdbc.update("delete from book_authors where book_id = :p#Id", paramsForDeleteBook);
        int deleted_rows = jdbc.update("delete from books where id = :p#Id", paramsForDeleteBook);
        return deleted_rows;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> selectedBooks = jdbc.query("select b.id, b.book_name, b.description, g.id genre_id, g.genre, string_agg(authors.fullname, ';') authorNames " +
                "from books b join book_genres g " +
                "on b.genre_id = g.id " +
                "left join " +
                "(select ba.book_id, a.id, a.fullname, a.description " +
                "from book_authors ba join authors a " +
                "on ba.author_id = a.id) authors " +
                "on b.id = authors.book_id " +
                "group by b.id, b.book_name, b.description, g.id, g.genre", new BookMapper());
        return selectedBooks;
    }

    private Book getBookById(long id) {
        SqlParameterSource paramsForSelectBook = new MapSqlParameterSource().addValue("p#Id", id);
        Book selectedBook = jdbc.queryForObject("select b.id, b.book_name, b.description, g.id genre_id, g.genre, string_agg(authors.fullname, ';') authorNames " +
                "from books b join book_genres g " +
                "on b.genre_id = g.id " +
                "left join " +
                "(select ba.book_id, a.id, a.fullname, a.description " +
                "from book_authors ba join authors a " +
                "on ba.author_id = a.id) authors " +
                "on b.id = authors.book_id " +
                "where b.id = :p#Id " +
                "group by b.id, b.book_name, b.description, g.id, g.genre", paramsForSelectBook, new BookMapper());
        return selectedBook;
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookGenre bookGenre = new BookGenre(rs.getLong("genre_id"), rs.getString("genre"));
            List<Author> authorList = authorDao.collectingAuthors(rs.getString("authorNames"), 0);
            long id = rs.getLong("id");
            String bookName = rs.getString("book_name");
            String description = rs.getString("description");
            return new Book(id, bookName, authorList, bookGenre, description);
        }
    }
}
