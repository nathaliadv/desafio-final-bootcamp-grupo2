INSERT INTO tb_user (dtype, id, email, name, password)
VALUES ('Representative', 1, 'fulano@email.com', 'Fulano',
        '$2a$10$c68AsV1lai7YKKg5g/QWEevpsIaQit6vsmcz4jPwjSYyTGrRPhvgq'), -- albertinho123
       ('Representative', 2, 'nathalia@email.com', 'Nathalia',
        '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'), -- nat123
       ('Buyer', 3, 'rod@email.com', 'Rodrigo',
        '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'), -- nat123
       ('Buyer', 4, 'luan@email.com', 'Luazinho',
        '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'), -- nat123
       ('Seller', 5, 'aderson@email.com', 'Aderson',
        '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'), -- nat123
       ('Seller', 6, 'gabriel@email.com', 'Gabriel',
        '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'); -- nat123


INSERT INTO tb_product_type (type)
VALUES ('FRESH'),
       ('COLD'),
       ('FREEZE');


INSERT INTO tb_product (name, volume, product_type_id)
VALUES ('Banana Prata', 1, 1),
       ('Banana Nanica', 1, 1),
       ('Melância', 3, 1),
       ('Maçã', 1, 1),
       ('Peça de Carne', 2, 3),
       ('Linguiça', 1, 3),
       ('Sorvete de Chocolate', 1, 3),
       ('Queijo Mussarela', 1, 2),
       ('Salame', 1, 2),
       ('Queijo Mineiro', 1, 2);


INSERT INTO tb_advertising (free_shipping, price, description, product_id, seller_id)
VALUES (true, 20.0, 'Melancia gostosinha nham nham', 3, 5),
       (false, 25.0, 'Sorvetinho gostosinho nham nham', 7, 6),
       (true, 5.0, 'Queijinho gostosinho nham nham', 8, 5),
       (true, 10.0, 'Salaminho gostosinho nham nham', 9, 6),
       (false, 2.50, 'Maçazinha gostosinha nham nham', 4, 5),
       (false, 300.50, 'Carninha gostosinha nham nham', 5, 6);


INSERT INTO tb_address (street, city, state, country, postal_code)
VALUES ('Av. das Nações Unidas', 'Osasco', 'SP', 'Brasil', 06233200),
       ('Rod. José Carlos Daux', 'Florianópolis', 'SC', 'Brasil', 88032005);


INSERT INTO tb_warehouse (name, address_id)
VALUES ('Armazem Melicidade', 1),
       ('Armazem Floripa', 2);


INSERT INTO tb_section (description, name, temperature, volume, product_type_id, representative_id, warehouse_id)
values ('Setor de Produtos Fresco do Tipo Fruta', 'SETOR01-SP', 15.0, 500, 1, 1, 1),
       ('Setor de Produtos Fresco', 'SETOR02-SP', 18.0, 500, 1, 1, 1),
       ('Setor de Produtos Frios', 'SETOR03-SP', 10.0, 500, 2, 1, 1),
       ('Setor de Produtos Congelados', 'SETOR04-SP', 15.0, 500, 3, 2, 2),
       ('Setor de Produtos Fresco do Tipo Fruta', 'SETOR01-SC', 15.0, 500, 1, 2, 2),
       ('Setor de Produtos Fresco', 'SETOR02-SC', 18.0, 500, 1, 2, 2);


INSERT INTO tb_purchase_status
VALUES (1, 'APPROVED');
INSERT INTO tb_purchase_status
VALUES (2, 'PENDING');
INSERT INTO tb_purchase_status
VALUES (3, 'REJECTED');
INSERT INTO tb_purchase_status
VALUES (4, 'DELIVERED');

INSERT INTO tb_inbound_order
VALUES (1, current_date(), 1);

INSERT INTO tb_batch
VALUES (1, 15, 10, '2023-12-29', 15, '2022-01-22', '05:30:00', 10, 5, 1),
       (2, 15, 10, '2023-12-29', 15, '2022-01-22', '05:30:00', 10, 5, 1),
       (3, 15, 10, '2023-12-29', 15, '2022-01-22', '05:30:00', 10, 1, 1),
       (4, 15, 10, '2023-12-29', 15, '2022-01-22', '05:30:00', 10, 1, 1);

INSERT INTO tb_purchase_order
VALUES (1, current_date(), 3, 1),
       (2, current_date(), 3, 4),
       (3, current_date(), 3, 4);

INSERT INTO tb_purchase_itens
VALUES (1, 15, 5, 1),
       (2, 5, 1, 1),
       (3, 1, 1, 2),
       (4, 5, 5, 2),
       (5, 2, 1, 3),
       (6, 5, 5, 3);

INSERT INTO tb_return_status
VALUES (1, 'APPROVED');
INSERT INTO tb_return_status
VALUES (2, 'PENDING');
INSERT INTO tb_return_status
VALUES (3, 'REJECTED');
INSERT INTO tb_return_status
VALUES (4, 'CANCELLED');

INSERT INTO tb_return_cause
VALUES (1, 'WITHDRAWAL');
INSERT INTO tb_return_cause
VALUES (2, 'DEFECT');

INSERT INTO tb_return_order
VALUES (1, current_date(), 3, 2, 1),
       (2, current_date(), 3, 2, 1);

INSERT INTO tb_return_order_itens
VALUES (1, 1, 3, 1),
       (2, 5, 4, 1),
       (3, 2, 5, 2),
       (4, 5, 6, 2);


INSERT INTO tb_vehicle
VALUES (10,'Teste Placa1', current_date(),100,'Teste Modelo',1,1),
       (1,'Teste Placa', current_date(),100,'Teste Modelo',1,1),
       (2,'Teste Placa2', current_date(),100,'Teste Modelo',1,1);