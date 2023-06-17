CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers values (1, 'Ivanov', 'Ivan', 21),
                             (2, 'Sidorov', 'Dmitryi', 21),
                             (3, 'Stepanov', 'Victor', 21),
                             (4, 'Kulagin', 'Seva', 24),
                             (5, 'Petrov', 'Stepan', 35),
                             (6, 'Jirnov', 'Vlad', 28),
                             (7, 'Samed', 'Intic', 30);

select * from customers
where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders values (1, 3, 1),
                          (2, 5, 3),
                          (3, 7, 5);

select * from customers
where id not in (select customer_id from orders);