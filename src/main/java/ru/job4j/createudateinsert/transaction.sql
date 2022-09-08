create table book (
    book_id serial primary key,
    name varchar(50),
    amount integer default 0,
    price integer
);

insert into book (name, amount, price) VALUES ('book_1', 3, 50);
insert into book (name, amount, price) VALUES ('book_2', 15, 32);
insert into book (name, amount, price) VALUES ('book_3', 8, 115);

insert into book (name, amount, price) VALUES ('book_4', 11, 64);
delete from book where price = 115;
update book set price = 75 where name = 'book_1';
update book set amount = 26 where name = 'book_2';

select sum(amount) from book;
update book set amount = 26 where name = 'book_1';
delete from book;