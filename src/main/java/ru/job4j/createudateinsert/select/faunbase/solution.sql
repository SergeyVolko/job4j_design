--1) Извлечение данных, у которых имя name содержит подстроку fish
SELECT * FROM fauna WHERE name LIKE '%fish%';
--2) Извлечение данных, у которых сред. продолжительность жизни находится в диапазоне 10 000 и 21 000
SELECT * FROM fauna WHERE avg_age >= 10000 AND avg_age <= 21000;
--3) Извлечение данных, у которых дата открытия не известна (null)
SELECT * FROM fauna WHERE discovery_date IS NULL;
--4) Извлечение данных видов, у которых дата открытия раньше 1950 года
SELECT * FROM fauna WHERE discovery_date < '01.01.1950';