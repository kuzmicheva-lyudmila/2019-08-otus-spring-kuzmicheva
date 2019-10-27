drop table if exists book_authors;
drop table if exists authors;
drop table if exists book_genres;
drop table if exists books;

create table authors (
    id serial Primary Key,
    fullname varchar(240) not null,
    descirption varchar(2000)
);

create table book_genres (
    id serial primary key,
    genre varchar(240) unique not null
);

create table books (
    id serial primary key,
    title varchar(240) not null,
    genre_id integer not null references book_genres(id),
    description varchar(2000)
);

create table book_authors (
    id serial primary key,
    book_id integer not null references books(id),
    author_id integer not null references authors(id)
);

insert into book_genres (genre) values ('romance');
insert into book_genres (genre) values ('history');
insert into book_genres (genre) values ('horror');
insert into book_genres (genre) values ('children''s books');

