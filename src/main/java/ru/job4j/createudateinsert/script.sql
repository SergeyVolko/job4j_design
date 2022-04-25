DROP TABLE nomenclature; 
CREATE TABLE nomenclature(
	id serial PRIMARY KEY,
	name VARCHAR(255),
	description text,
	price money
);
INSERT INTO nomenclature(name, description, price) VALUES('Ручка', 'Синяя', 100.50);
UPDATE nomenclature set price = 90.20;
DELETE FROM nomenclature;
SELECT * FROM nomenclature;