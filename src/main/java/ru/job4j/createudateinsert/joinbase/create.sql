CREATE TABLE nomenclature(
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE specifications(
	id serial PRIMARY KEY,
	country VARCHAR(255),
	price money,
	name_id int REFERENCES nomenclature(id)
);