CREATE TABLE author (
	author_id serial PRIMARY KEY,
	name_author varchar(50)
);

INSERT INTO author(name_author)
VALUES
('Булгаков М.А.'),
('Достоевский Ф.М.'),
('Есенин С.А.'),
('Пастернак Б.Л.'),
('Лермонтов М.Ю.');

CREATE TABLE genre (
	genre_id serial PRIMARY KEY,
	name_genre varchar(50)
);

INSERT INTO genre(name_genre)
VALUES
('Роман'),
('Поэзия'),
('Приключения');

CREATE TABLE book(
	book_id serial PRIMARY KEY,
	title varchar(50),
	author_id integer REFERENCES author(author_id),
	genre_id integer REFERENCES genre(genre_id),
	price money,
	amount integer
);

INSERT INTO book(title, author_id, genre_id, price, amount)
VALUES
('Мастер и Маргарита', 1, 1, 670.99, 3),
('Белая гвардия', 1, 1, 540.50, 5),
('Идиот', 2, 1, 460.00, 10),
('Братья Карамазовы', 2, 1, 799.01,	2),
('Игрок', 2, 1, 480.50, 10),
('Стихотворения и поэмы', 3, 2, 650.00, 15),
('Черный человек', 3, 2, 570.20, 6),
('Лирика', 4, 2, 518.99, 2);

CREATE TABLE city(
	city_id	serial PRIMARY KEY,
	name_city varchar(50),
	days_delivery integer
);

INSERT INTO city(name_city, days_delivery)
VALUES
('Москва', 5),
('Санкт-Петербург', 3),
('Владивосток', 12);

CREATE TABLE client(
	client_id serial PRIMARY KEY,
	name_client varchar(50),
	city_id integer REFERENCES city(city_id),
	email varchar(50)
);

INSERT INTO client(name_client, city_id, email)
VALUES
('Баранов Павел', 3, 'baranov@test'),
('Абрамова Катя', 1, 'abramova@test'),
('Семенонов Иван', 2, 'semenov@test'),
('Яковлева Галина', 1, 'yakovleva@test');

CREATE TABLE buy(
	buy_id serial PRIMARY KEY,
	buy_description varchar(100),
	client_id integer REFERENCES client(client_id)
);

INSERT INTO buy(buy_description, client_id) 
VALUES
('Доставка только вечером', 1),
(NULL, 3),
('Упаковать каждую книгу по отдельности', 2),
(NULL, 1);

CREATE TABLE buy_book(
	buy_book_id serial PRIMARY KEY,
	buy_id integer REFERENCES buy(buy_id),
	book_id integer REFERENCES book(book_id),
	amount integer
);

INSERT INTO buy_book(buy_id, book_id, amount)
VALUES
(1, 1, 1),
(1, 7, 2),
(1, 3, 1),
(2, 8, 2),
(3, 3, 2),
(3, 2, 1),
(3, 1, 1),
(4, 5, 1);

CREATE TABLE step(
	step_id serial PRIMARY KEY,
	name_step varchar(50)
);

INSERT INTO step(name_step)
VALUES
('Оплата'),
('Упаковка'),
('Транспортировка'),
('Доставка');

CREATE TABLE buy_step(
	buy_step_id serial PRIMARY KEY,
	buy_id integer REFERENCES buy(buy_id),
	step_id integer REFERENCES step(step_id),
	date_step_beg date,
	date_step_end date
);

INSERT INTO buy_step(buy_id, step_id, date_step_beg, date_step_end)
VALUES
(1, 1, '2020-02-20', '2020-02-20'),
(1, 2, '2020-02-20', '2020-02-21'),
(1, 3, '2020-02-22', '2020-03-07'),
(1,	4, '2020-03-08', '2020-03-08'),
(2, 1, '2020-02-28', '2020-02-28'),
(2,	2, '2020-02-29', '2020-03-01'),
(2,	3, '2020-03-02', NULL),
(2,	4, NULL, NULL),
(3,	1, '2020-03-05', '2020-03-05'),
(3,	2, '2020-03-05', '2020-03-06'),
(3,	3, '2020-03-06', '2020-03-10'),
(3,	4, '2020-03-11', NULL),
(4,	1, '2020-03-20', NULL),
(4,	2, NULL, NULL),
(4,	3, NULL, NULL),
(4,	4, NULL, NULL);

CREATE VIEW client_read_dostoevski AS 
SELECT 
  DISTINCT name_client
FROM client
     INNER JOIN buy USING(client_id)
     INNER JOIN buy_book USING(buy_id)
     INNER JOIN book USING(book_id)
     INNER JOIN author USING(author_id)
WHERE name_author = 'Достоевский Ф.М.'
ORDER BY name_client;

SELECT * FROM client_read_dostoevski;
