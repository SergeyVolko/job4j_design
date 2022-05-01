--Оператор select
--SELECT * FROM students; 
--SELECT name, course, speciality FROM students;

--Фильтрация
--SELECT * FROM students WHERE course = 2;
--SELECT * FROM students WHERE course != 2;
--SELECT * FROM students WHERE name IS NULL;
--SELECT * FROM students WHERE name IS NOT NULL;
--SELECT * FROM students WHERE enroll_date > '01.09.2020';
--SELECT * FROM students WHERE course > 2;

--Оператор сравнения строк LIKE
--SELECT * FROM students WHERE name LIKE 'D%'; 

--Логические операторы AND, OR
--SELECT * FROM students WHERE name LIKE 'D%' AND course > 2;
--SELECT * FROM students WHERE name LIKE 'D%' OR course > 2; 

--Работа с датами
--SELECT CURRENT_DATE; -- получение текущей даты
--SELECT CURRENT_DATE > '10.09.2020'; -- проверка, что дата позже 10.09.2020
--SELECT CURRENT_DATE + INTERVAL'1 month'; -- прибавление какой-то единицы времени (day, week, month, year, hour)

--Упорядочивание выборки ORDER BY
--SELECT * FROM students ORDER BY speciality ASC;
--SELECT * FROM students ORDER BY speciality DESC; 

--Выборка уникальных элементов SELECT DISTINCT
--SELECT DISTINCT course FROM students;

--Выборка определенного числа элементов LIMIT
--SELECT * FROM students LIMIT 5; 