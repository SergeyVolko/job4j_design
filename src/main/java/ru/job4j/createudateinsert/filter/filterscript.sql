create table types_product(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references types_product(id),
	expired_date date,
	price float
);

insert into types_product(name) 
values ('СЫР'), ('МОЛОКО');

insert into product(name, type_id, expired_date, price)
values ('Сыр моцарела', 1, date '2022-06-01', 300), ('Сыр моцарела', 1, date '2022-04-01', 100),
('Сыр моцарела', 1, date '2022-04-01', 10), ('Сыр моцарела', 1, date '2022-03-01', 10),
('Сыр моцарела', 1, date '2022-07-01', 400), ('Сыр моцарела', 1, date '2022-02-01', 10),
('Сыр моцарела', 1, date '2022-06-20', 350), ('Сыр моцарела', 1, date '2022-06-20', 350),
('Сыр моцарела', 1, date '2022-06-01', 300), ('Сыр моцарела', 1, date '2022-04-01', 100),
('Сыр моцарела', 1, date '2022-06-01', 300), ('Сыр моцарела', 1, date '2022-04-01', 100),
('Сыр плавленый', 1, date '2022-05-01', 50), ('Сыр плавленый', 1, date '2022-06-01', 100),
('Морженное стаканчик', 2, date '2022-05-10', 150), ('Морженное стаканчик', 2, date '2022-04-10', 10),
('Морженное стаканчик', 2, date '2022-06-10', 200), ('Морженное стаканчик', 2, date '2022-06-15', 200),
('Кефир', 2, date '2022-04-10', 10), ('Кефир', 2, date '2022-05-20', 10);  

--1. Написать запрос получение всех продуктов с типом "СЫР"
select 
	p.name as Наименование,
	tp.name as ТипПродукта,
	p.expired_date as СрокГодности,
	p.price as Цена
from product as p join types_product as tp on p.type_id = tp.id
where tp.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select 
	p.name as Наименование,
	tp.name as ТипПродукта,
	p.expired_date as СрокГодности,
	p.price as Цена
from product as p join types_product as tp on p.type_id = tp.id
where p.name like '%Морженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select 
	p.name as Наименование,
	tp.name as ТипПродукта,
	p.expired_date as СрокГодности,
	p.price as Цена
from product as p join types_product as tp on p.type_id = tp.id
where p.expired_date < current_date;

--4. Написать запрос, который выводит самый дорогой продукт.
select 
	p.name as Наименование,
	p.price as Цена
from product as p order by price desc limit 1;

--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. 
--В виде имя_типа, количество
select
	tp.name as ТипПродукта,
	count(p.name) as Количество
from product as p join types_product as tp on p.type_id = tp.id
group by tp.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select 
	p.name as Наименование,
	tp.name as ТипПродукта,
	p.expired_date as СрокГодности,
	p.price as Цена
from product as p join types_product as tp on p.type_id = tp.id
where tp.name = 'СЫР' or tp.name = 'МОЛОКО';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
--Под количеством подразумевается количество продуктов определенного типа. 
--Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", 
--которые ему принадлежат, то количество продуктов типа "СЫР" будет 2. 
select
	tp.name as ТипПродукта
from product as p join types_product as tp on p.type_id = tp.id
group by tp.name
having count(p.name) < 10;

--8. Вывести все продукты и их тип.
select distinct 
	p.name as Наименование,
	tp.name as ТипПродукта
from product as p join types_product as tp on p.type_id = tp.id;