create table authors (
    id identity Primary Key,
    fullname varchar(240) not null,
    description varchar(2000)
);

create table book_genres (
    id identity primary key,
    genre varchar(240) unique not null
);

create table books (
    id identity primary key,
    book_name varchar(240) not null,
    genre_id integer not null references book_genres(id),
    description varchar(2000)
);

create table book_authors (
    id identity primary key,
    book_id integer not null references books(id),
    author_id integer not null references authors(id)
);
