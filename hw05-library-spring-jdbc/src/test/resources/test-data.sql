insert into book_genres (id, genre) values (1, 'romance');
insert into book_genres (id, genre) values (2, 'history');
insert into book_genres (id, genre) values (3, 'horror');
insert into book_genres (id, genre) values (4, 'children''s books');

insert into authors (id, fullname) values (1, 'author1');
insert into authors (id, fullname) values (2, 'author2');

insert into books (book_name, genre_id) values ('First book', 1);

commit;
