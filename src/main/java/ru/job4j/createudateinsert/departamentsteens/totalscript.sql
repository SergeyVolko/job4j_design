create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	departament_id int references departments(id)
);

insert into departments(name) 
values ('Dep 1'), ('Dep 2'), ('Dep 3'), ('Dep 4'), ('Dep 5');
insert into employees(name, departament_id) 
values ('Employ 1', 1), ('Employ 2', 2), ('Employ 3', 3),('Employ 4', 4),
('Employ 5', 1), ('Employ 6', 2), ('Employ 7', null),('Employ 8', null);

--2. Выполнить запросы с left, rigth, full, cross соединениями
select * from departments as d left join employees as e on d.id = e.departament_id;
select * from departments as d right join employees as e on d.id = e.departament_id;
select * from departments as d full join employees as e on d.id = e.departament_id;
select * from departments as d cross join employees as e;

--3. Используя left join найти департаменты, у которых нет работников
select 
	d.name
from departments as d left join employees as e on d.id = e.departament_id
where e.departament_id is NULL;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат 
--(порядок вывода колонок в эти запросах также должен быть идентичный).
select * from departments as d left join employees as e on d.id = e.departament_id; 
select * from employees as e right join departments as d on d.id = e.departament_id;
select * from employees as e left join departments as d on d.id = e.departament_id; 
select * from departments as d right join employees as e on d.id = e.departament_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join 
--составить все возможные разнополые пары
create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender)
values ('people 1', 'M'), ('people 2', 'M'), ('people 3', 'M'),
('people 4', 'W'), ('people 5', 'W'), ('people 6', 'W');

select * from teens as t1 cross join teens as t2
where t1.gender != t2.gender;