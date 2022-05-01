INSERT INTO roles(name) VALUES('administrator');
INSERT INTO roles(name) VALUES('senior salesman');
INSERT INTO roles(name) VALUES('salesman');

INSERT INTO users(name, role_id) VALUES('Sergey Ivanov', 1);
INSERT INTO users(name, role_id) VALUES('Grigoriy Kotovski', 2);
INSERT INTO users(name, role_id) VALUES('Stepan Petrov', 3);
INSERT INTO users(name, role_id) VALUES('Vladimir Shwec', 3);

INSERT INTO rules(name) VALUES('reading');
INSERT INTO rules(name) VALUES('record');
INSERT INTO rules(name) VALUES('addendum');
INSERT INTO rules(name) VALUES('change');
INSERT INTO rules(name) VALUES('removal');

INSERT INTO role_rules(name, role_id, rule_id) VALUES('administrator - reading', 1, 1);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('administrator - record', 1, 2);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('administrator - addendum', 1, 3);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('administrator - change', 1, 4);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('administrator - removal', 1, 5);

INSERT INTO role_rules(name, role_id, rule_id) VALUES('senior salesman - reading', 2, 1);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('senior salesman - record', 2, 2);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('senior salesman - addendum', 2, 3);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('senior salesman - change', 2, 4);

INSERT INTO role_rules(name, role_id, rule_id) VALUES('salesman - reading', 3, 1);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('salesman - record', 3, 2);
INSERT INTO role_rules(name, role_id, rule_id) VALUES('salesman - addendum', 3, 3);

INSERT INTO category(name) VALUES('product');
INSERT INTO category(name) VALUES('service');

INSERT INTO state_item(name) VALUES('completed');
INSERT INTO state_item(name) VALUES('in progress');
INSERT INTO state_item(name) VALUES('cancelled');

INSERT INTO items(name, user_id, category_id, state_item_id) VALUES('Order 5 pens', 3, 1, 1);
INSERT INTO items(name, user_id, category_id, state_item_id) VALUES('Computer repair', 4, 2, 2);

INSERT INTO comments_item(comment_item, item_id) VALUES('Black handles', 1);
INSERT INTO comments_item(comment_item, item_id) VALUES('Computer Lenovo', 2);

INSERT INTO attachs(name, item_id) VALUES('pen.jpeg', 1);
INSERT INTO attachs(name, item_id) VALUES('computer.jpeg', 2);