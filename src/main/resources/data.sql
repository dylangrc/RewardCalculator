INSERT INTO customer (id, name)
VALUES (1, 'user1');
INSERT INTO customer (id, name)
VALUES (2, 'user2');
INSERT INTO customer (id, name)
VALUES (3, 'user3');

INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (1, 2, '2023-01-23', 100);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (2, 2, '2023-01-15', 150);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (3, 2, '2022-02-23', 80);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (4, 2, '2022-02-22', 30);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (5, 2, '2022-11-30', 300);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (6, 2, '2022-10-01', 120);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (7, 2, '2022-08-01', 90);

INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (8, 3, '2021-07-01', 190);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (9, 3, '2021-08-01', 40);
INSERT INTO transaction (id, customer_id, transaction_time, amount) VALUES (10, 3, '2021-08-01', 200);
