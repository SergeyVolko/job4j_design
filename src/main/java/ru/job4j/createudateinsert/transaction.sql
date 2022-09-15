create table book (
    book_id serial primary key,
    name varchar(50),
    amount integer default 0,
    price integer
);

insert into book (name, amount, price) VALUES ('book_1', 3, 50);
insert into book (name, amount, price) VALUES ('book_2', 15, 32);
insert into book (name, amount, price) VALUES ('book_3', 8, 115);

select * from book;
begin transaction;
delete from book where price = 115;
update book set price = 75 where name = 'book_1';
update book set amount = 26 where name = 'book_2';
select * from book;
rollback transaction;
select * from book;

begin transaction;
select * from book;
insert into book (name, amount, price) VALUES ('book_4', 11, 64);
savepoint first_savepoint;
select * from book;
delete from book where price = 115;
savepoint second_savepoint;
update book set amount = 26 where name = 'book_2';
select * from book;
savepoint third_savepoint;
insert into book (name, amount, price) VALUES ('book_5', 33, 33);
select * from book;
rollback to third_savepoint;
select * from book;
rollback to second_savepoint;
select * from book;
rollback to first_savepoint;
select * from book;
rollback transaction;
select * from book;