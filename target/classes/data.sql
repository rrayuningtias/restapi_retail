INSERT INTO S_USER (ID, USERNAME, PASSWORD, ACTIVE) VALUES
('a001', 'roro', '$2a$12$TfbyVmaxfkPGSo2IIkhSyeuAAQPEfGXl7RGyEGbwPxL4Q8qj9lZMy', true),
('a002', 'bambank', '$2a$12$5mmpUZQhgfXzwecNqhkfW.q.S8CXLlAVxXwjZjBWC0EpJ5MSUlUDm', true);

INSERT INTO ROLE (ID, ROLE_NAME) VALUES
('cust', 'ROLE_CUSTOMER'),
('adm', 'ROLE_ADMIN');

INSERT INTO USERS_ROLE (ID_USERS, ID_ROLES) VALUES
('a001', 'adm'),
('a002', 'cust');

SELECT USERNAME, PASSWORD, ACTIVE AS enabled FROM S_USER WHERE USERNAME = 'roro';

SELECT A.USERNAME, R.ROLE_NAME as authority FROM S_USER A JOIN USERS_ROLE RA ON A.ID = RA.ID_USERS JOIN ROLE R ON RA.ID_ROLES = R.ID WHERE A.USERNAME = 'roro';

insert into User (USER_id, username, saldo) values
(1, 'roro', 10000000),
(2, 'adila', 10000000),
(3, 'sungjin', 10000000),
(4, 'bambank', 10000000),
(5, 'brian', 10000000),
(6, 'sapa', 1000);

insert into Warehouse (WAREHOUSE_id, nama_barang, harga_barang, stock_barang) values
(1, 'EXplOration', 1500000, 100),
(2, 'SMTOWN LIVE', 1000000, 100),
(3, 'Asian Sound Syndicate', 575000, 100),
(4, 'Day6 GRAVITY', 1200000, 100),
(5, 'NEO CITY', 800000, 100),
(6, 'SUPER SHOW', 1400000, 100),
(7, 'TVXQ Concert', 1250000, 100),
(8, 'EXO CBX', 990000, 100),
(9, 'SHINEE WORLD', 750000, 100),
(10, 'mie', 5000, 3);

insert into Transaction (TRANSACTION_id, USER_id, WAREHOUSE_id, qty_Transaction, total_Transaction, tanggal, username, nama_barang, saldo) values
(1, 4, 1, 2, 3000000, '2019-06-18', 'roro', 'EXplOration', 7000000),
(2, 2, 3, 3, 1725000, '2019-06-18', 'adila', 'Asian Sound Syndicate', 8275000),
(3, 1, 6, 4, 5600000, '2019-06-18', 'sungjin', 'SUPER SHOW', 4400000);