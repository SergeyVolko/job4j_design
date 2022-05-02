create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) 
values ('Sony', 500.00), ('Xiaomy', 1000.00), ('Samsung', 2000.00);

insert into people(name) 
values ('Sergey'), ('Vladimir'), ('Nicolay'), ('Ivan');

insert into devices_people(device_id, people_id)
values (1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 1), (3, 2);

select avg(price) from devices;

select
	p.name,
	avg(d.price)
from devices_people as dp join devices as d on dp.device_id = d.id
	join people as p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;