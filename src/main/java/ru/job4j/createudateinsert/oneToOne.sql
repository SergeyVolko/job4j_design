create table users_system(
	id serial primary key,
	name varchar(255)
);

create table authorization_pages(
	id serial primary key,
	login varchar(255),
	password_user int
);

create table accounts(
	id serial primary key,
	user_system_id int references users_system(id) unique,
	authorization_page_id int references authorization_pages(id) unique
);

insert into users_system(name) values('Ivan');
insert into authorization_pages(login, password_user) values('Cat', 123);
insert into accounts(user_system_id, authorization_page_id) values(1, 1);

select * from accounts;