SELECT * FROM nomenclature AS n JOIN specifications s ON n.id = s.id;

SELECT 
	n.name,
	s.country,
	s.price	
FROM nomenclature AS n JOIN specifications AS s ON n.id = s.id;

SELECT 
	n.name AS Наименование,
	s.country AS Страна,
	s.price AS Цена	
FROM nomenclature AS n JOIN specifications AS s ON n.id = s.id;
