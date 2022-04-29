drop table securing_cars;
drop table car;
drop table driver;

create table car(
	id serial primary key,
	name varchar(255)
);

create table driver(
	id serial primary key,
	name varchar(255)
);

create table securing_cars(
	id serial primary key,
	car_id int references car(id),
	driver_id int references driver(id)
);

insert into car(name) values('Reno');
insert into car(name) values('KIA');
insert into driver(name) values('Jim');
insert into driver(name) values('Mark');

insert into securing_cars(car_id, driver_id) values(1, 1);
insert into securing_cars(car_id, driver_id) values(1, 2);
insert into securing_cars(car_id, driver_id) values(2, 1);

select * from securing_cars;



