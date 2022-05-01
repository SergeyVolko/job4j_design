CREATE TABLE IF NOT EXISTS roles(
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users(
	id serial PRIMARY KEY,
	name VARCHAR(255),
	role_id int REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS rules(
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS role_rules(
	id serial PRIMARY KEY,
	name VARCHAR(255),
	role_id int REFERENCES roles(id),
	rule_id int REFERENCES rules(id)
);

CREATE TABLE IF NOT EXISTS category(
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS state_item (
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS items(
	id serial PRIMARY KEY,
	name VARCHAR(255),
	user_id int REFERENCES users(id),
	category_id int REFERENCES category(id),
	state_item_id int REFERENCES state_item(id)
);

CREATE TABLE IF NOT EXISTS comments_item(
	id serial PRIMARY KEY,
	comment_item text,
	item_id int REFERENCES items(id)
);

CREATE TABLE IF NOT EXISTS attachs(
	id serial PRIMARY KEY,
	name VARCHAR(255),
	item_id int REFERENCES items(id)
);