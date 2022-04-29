drop table nomenclature;
drop table item_type;
create table item_type(
	id serial primary key,
	name varchar(255)
);

create table nomenclature( 
	id serial primary key,
	name varchar(255),
	item_type_id int references item_type(id)
);

insert into item_type(name) values('product');
insert into item_type(name) values('service');
insert into nomenclature(name, item_type_id) values('pen', 1);
insert into nomenclature(name, item_type_id) values('delivery', 2);
select * from nomenclature;