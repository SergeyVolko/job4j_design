CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer REFERENCES company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company 
VALUES (1, 'company 1'), (2, 'company 2'), (3, 'company 3'), (4, 'company 4'), (5, 'company 5');

INSERT INTO person
VALUES (1, 'person 1', 1), (2, 'person 2', 1), (3, 'person 3', 1), (4, 'person 4', 2), 
(5, 'person 5', 2), (6, 'person 6', 2), (7, 'person 7', 2), (8, 'person 8', 3), 
(9, 'person 9', 3), (10, 'person 10', 4), (11, 'person 11', 4), (12, 'person 12', 4);

-- 1. В одном запросе получить
-- имена всех person, которые не состоят в компании с id = 5;
-- название компании для каждого человека.
SELECT person.name AS person, company.name AS company FROM person
LEFT JOIN company ON person.company_id = company.id
WHERE company_id <> 5;

--2. Необходимо выбрать название компании с максимальным количеством человек + количество человек 
--в этой компании(нужно учесть, что таких компаний может быть несколько).
SELECT company.name, COUNT(person.name) FROM company
LEFT JOIN person ON person.company_id = company.id
GROUP BY company.name
HAVING COUNT(person.name) = ANY(SELECT COUNT(name) FROM person
							    GROUP BY company_id
                                ORDER BY COUNT(name) DESC
                                LIMIT 1);
