create table bodyworks(
	id serial primary key,
	name varchar(255)
);

create table engines(
	id serial primary key,
	name varchar(255)
);

create table transmissions(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255),
	bodywork_id int references bodyworks(id),
	engine_id int references engines(id), 
	transmission_id int references transmissions(id)
	
);

insert into bodyworks(name) 
values ('bodywork 1'), ('bodywork 2'), ('bodywork 3'), ('bodywork 4'), ('bodywork 5'); 
insert into engines(name) 
values ('engine 1'), ('engine 2'), ('engine 3'), ('engine 4'), ('engine 5'); 
insert into transmissions(name) 
values ('transmission 1'), ('transmission 2'), ('transmission 3'), ('transmission 4'), ('transmission 5');
insert into cars(name, bodywork_id, engine_id, transmission_id) 
values ('car 1', 1, 1, 1), ('car 2', 2, 2, 2), ('car 3', 1, 2, 3), ('car 4', 2, 3, 4), ('car 5',5, 4, 2),
('car 6', null, 2, 3), ('car 7', 2, null, 2);

--1) Вывести список всех машин и все привязанные к ним детали. 
--Нужно учесть, что каких-то деталей машина может и не содержать.
select 
	c.name as Машина,
	b.name as Кузов,
	e.name as Двигатель,
	t.name as Трансмиссия
from cars as c 
	left join bodyworks as b on c.bodywork_id = b.id
	left join engines as e on c.engine_id = e.id
	left join transmissions as t on c.transmission_id = t.id;
--2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, 
--кузова, двигатели, коробки передач.
select
	b.name
from bodyworks as b left join cars as c on b.id = c.bodywork_id
where c.bodywork_id is null
union
select
	e.name
from engines as e left join cars as c on e.id = c.engine_id
where c.engine_id is null
union
select
	t.name
from transmissions as t left join cars as c on t.id = c.transmission_id
where c.transmission_id is null;