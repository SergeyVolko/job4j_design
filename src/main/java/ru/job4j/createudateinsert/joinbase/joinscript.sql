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

INSERT INTO nomenclature(name) VALUES('Pen');
INSERT INTO nomenclature(name) VALUES('Computer');
INSERT INTO nomenclature(name) VALUES('Table');
INSERT INTO nomenclature(name) VALUES('Door');

INSERT INTO specifications(country, price, name_id) VALUES('Russia', 10.00, 1);
INSERT INTO specifications(country, price, name_id) VALUES('Germany', 20.00, 1);
INSERT INTO specifications(country, price, name_id) VALUES('USA', 1000.00, 2);
INSERT INTO specifications(country, price, name_id) VALUES('China', 200.00, 2);

SELECT * FROM nomenclature AS n JOIN specifications s ON n.id = s.name_id;

SELECT 
	n.name,
	s.country,
	s.price	
FROM nomenclature AS n JOIN specifications AS s ON n.id = s.name_id;

SELECT 
	n.name AS Наименование,
	s.country AS Страна,
	s.price AS Цена	
FROM nomenclature AS n JOIN specifications AS s ON n.id = s.name_id;
