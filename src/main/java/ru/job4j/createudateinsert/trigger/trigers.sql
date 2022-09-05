create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
alter table products disable trigger discount_trigger;
select * from products;
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

--1)  Триггер должен срабатывать после вставки данных, для любого товара и
--просто насчитывать налог на товар (нужно прибавить налог к цене товара). 
--Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create or replace function nalog_statement()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
		raise notice '% %', 'nalog_statement ', new; 
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure nalog_statement();

insert into products (name, producer, count, price) VALUES ('product_st0', 'producer_st', 3, 1000);
select * from products;
-----------------------------------------------------------------
--2) Триггер должен срабатывать до вставки данных и насчитывать налог на 
--товар (нужно прибавить налог к цене товара). Здесь используем row уровень.
create or replace function nalog_row()
    returns trigger as
$$
    BEGIN
		NEW.price = 1.2 * NEW.price;
		raise notice '% %', 'nalog_row ', new; 
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_trigger_for_row
	before insert on products
	for each row
	execute procedure nalog_row();
-------------------------------------------------------------------------------------
drop trigger nalog_trigger_for_row on products;
drop trigger nalog_trigger on products;
drop trigger discount_trigger on products;
drop function nalog_row;
drop function nalog_statement;

select * from products;
delete from products;
--------------------------------------------------------------------------------
--3) Создайте таблицу:
--Нужно написать триггер на row уровне, который при вставке продукта в таблицу products, 
--будет заносить имя, цену и текущую дату в таблицу history_of_price.
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function for_history_price_row()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date) VALUES(NEW.name, NEW.price, current_date);
		raise notice '% %', 'for_history_price_row ', new; 
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_in_history_of_price
	after insert on products
	for each row
	execute procedure for_history_price_row();

insert into products (name, producer, count, price) VALUES ('product_history0', 'producer_histoty', 10, 100);
select * from history_of_price;