<<<<<<< Updated upstream
drop table if exists address;
drop table if exists advertising;
drop table if exists batch;
drop table if exists hibernate_sequence;
drop table if exists inbound_order;
drop table if exists product;
drop table if exists product_type;
drop table if exists purchase_itens;
drop table if exists purchase_order;
drop table if exists purchase_status;
drop table if exists section;
drop table if exists user;
drop table if exists warehouse;
=======
-- drop table if exists address;
-- drop table if exists advertising;
-- drop table if exists batch;
-- drop table if exists hibernate_sequence;
-- drop table if exists inbound_order;
-- drop table if exists perfil;
-- drop table if exists product;
-- drop table if exists product_type;
-- drop table if exists purchase_itens;
-- drop table if exists purchase_order;
-- drop table if exists purchase_status;
-- drop table if exists section;
-- drop table if exists user;
-- drop table if exists user_perfis;
-- drop table if exists warehouse;
>>>>>>> Stashed changes

-- CREATE TABLES ZONE

CREATE TABLE `address` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `city` varchar(255) DEFAULT NULL,
                           `country` varchar(255) DEFAULT NULL,
                           `postal_code` int NOT NULL,
                           `state` varchar(255) DEFAULT NULL,
                           `street` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ;

CREATE TABLE `product_type` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `type` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ;

CREATE TABLE `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `volume` double NOT NULL,
                           `product_type_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (`product_type_id`) REFERENCES product_type (`id`)
);

CREATE TABLE `tb_user` (
                           `dtype` varchar(31) NOT NULL,
                           `id` bigint NOT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ;

CREATE TABLE `advertising` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `description` varchar(255) DEFAULT NULL,
                               `price` decimal(19,2) DEFAULT NULL,
                               `product_id` bigint DEFAULT NULL,
                               `seller_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               FOREIGN KEY (`product_id`) REFERENCES product (`id`),
                               FOREIGN KEY (`seller_id`) REFERENCES tb_user (`id`)
) ;

CREATE TABLE `warehouse` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             FOREIGN KEY (`address_id`) REFERENCES address (`id`)
) ;

CREATE TABLE `section` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `description` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `temperature` double NOT NULL,
                           `volume` double NOT NULL,
                           `product_type_id` bigint DEFAULT NULL,
                           `representative_id` bigint DEFAULT NULL,
                           `warehouse_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (`product_type_id`) REFERENCES product_type (`id`),
                           FOREIGN KEY (`representative_id`) REFERENCES tb_user (`id`),
                           FOREIGN KEY (`warehouse_id`) REFERENCES warehouse (`id`)
) ;

CREATE TABLE `inbound_order` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `creation_date` date DEFAULT NULL,
                                 `section_id` bigint DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 FOREIGN KEY (`section_id`) REFERENCES section (`id`)
) ;

CREATE TABLE `batch` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `current_quantity` int NOT NULL,
                         `current_temperature` double NOT NULL,
                         `expiration_date` date DEFAULT NULL,
                         `initial_quantity` int NOT NULL,
                         `manufacturing_date` date DEFAULT NULL,
                         `manufacturing_time` time DEFAULT NULL,
                         `minimum_temperature` double NOT NULL,
                         `advertising_id` bigint DEFAULT NULL,
                         `inbound_order_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`advertising_id`) REFERENCES advertising (`id`),
                         FOREIGN KEY (`advertising_id`) REFERENCES inbound_order (`id`)
) ;

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ;



<<<<<<< Updated upstream
CREATE TABLE `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `volume` double NOT NULL,
                           `product_type_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKlabq3c2e90ybbxk58rc48byqo` (`product_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product_type` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `type` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `purchase_itens` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `quantity` int DEFAULT NULL,
                                  `advertising_id` bigint DEFAULT NULL,
                                  `purchase_order_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKst982bnnlcwwqv9k1m07eo630` (`advertising_id`),
                                  KEY `FKnt27l1o6trdlv1wet2f8ehnd3` (`purchase_order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
=======
CREATE TABLE `perfil` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ;

CREATE TABLE `purchase_status` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `status_code` varchar(255) DEFAULT NULL,
                                   PRIMARY KEY (`id`)
) ;
>>>>>>> Stashed changes

CREATE TABLE `purchase_order` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `date` date DEFAULT NULL,
                                  `buyer_id` bigint DEFAULT NULL,
                                  `purchase_status_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  FOREIGN KEY (`buyer_id`) REFERENCES tb_user (`id`),
                                  FOREIGN KEY (`purchase_status_id`) REFERENCES purchase_status (`id`)
) ;


<<<<<<< Updated upstream
CREATE TABLE `section` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `description` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `temperature` double NOT NULL,
                           `volume` double NOT NULL,
                           `product_type_id` bigint DEFAULT NULL,
                           `representative_id` bigint DEFAULT NULL,
                           `warehouse_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKfcskwr4v03ppj1vm6e9bivamp` (`product_type_id`),
                           KEY `FKfs3l22bqk50t8abl2jrgajqsm` (`representative_id`),
                           KEY `FKlimr8jdiu8ur1jp722gvhsb9t` (`warehouse_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
                        `dtype` varchar(31) NOT NULL,
                        `id` bigint NOT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `warehouse` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) DEFAULT NULL,
                             `address_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FKp7xymgre8vt94ihf75e9movyt` (`address_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
=======

CREATE TABLE `purchase_itens` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `quantity` int DEFAULT NULL,
                                  `advertising_id` bigint DEFAULT NULL,
                                  `purchase_order_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  FOREIGN KEY (`advertising_id`) REFERENCES  advertising (`id`),
                                  FOREIGN KEY (`purchase_order_id`) REFERENCES purchase_order (`id`)
) ;


>>>>>>> Stashed changes

-- INSERT ZONE

INSERT INTO
    product_type (type)
VALUES
    ('FRESH'), ('COLD'), ('FREEZE');


INSERT INTO
    product (name, volume, product_type_id)
VALUES
    ('Banana Prata', 1, 1), ('Banana Nanica', 1, 1), ('Melância', 3, 1),
    ('Maçã', 1, 1), ('Peça de Carne', 2, 3), ('Linguiça', 1, 3),
    ('Sorvete de Chocolate', 1, 3), ('Queijo Mussarela', 1, 2), ('Salame', 1, 2),
    ('Queijo Mineiro', 1, 2);


INSERT INTO
    tb_user (dtype, id, email, name, password)
VALUES
<<<<<<< Updated upstream
    ("Representative", 1, "fulano@email.com", "Fulano", "$2a$10$c68AsV1lai7YKKg5g/QWEevpsIaQit6vsmcz4jPwjSYyTGrRPhvgq"), -- albertinho123
    ("Representative", 2, "nathalia@email.com", "Nathalia", "$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e"), -- nat123
    ("Buyer", 3, "rod@email.com", "Rodrigo", "$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e"), -- nat123
    ("Buyer", 4, "lua@email.com", "Luazinho", "$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e"), -- nat123
    ("Seller", 5, "aderson@email.com", "Aderson", "$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e"); -- nat123
=======
    ('Representative', 1, 'fulano@email.com', 'Fulano', '$2a$10$c68AsV1lai7YKKg5g/QWEevpsIaQit6vsmcz4jPwjSYyTGrRPhvgq'), -- albertinho123
    ('Representative', 2, 'nathalia@email.com', 'Nathalia', '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'), -- nat123
    ('Buyer', 3, 'nathalia@email.com', 'Rodrigo', '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'), -- nat123
    ('Buyer', 4, 'lua@email.com', 'Luazinho', '$2a$10$OfyZEQrKF3zUQ5volj.m3.tNE6899EZKoIrE1LOh8U9I/wW8Ipg5e'); -- nat123
>>>>>>> Stashed changes

INSERT INTO
    advertising (price, description, product_id, seller_id)
VALUES
<<<<<<< Updated upstream
    (20.0, "Melancia gostosinha nham nham", 3, 5),
    (25.0, "Sorvetinho gostosinho nham nham", 7, 5),
    (5.0, "Queijinho gostosinho nham nham", 8, 5),
    (10.0, "Salaminho gostosinho nham nham", 9, 5),
    (2.50, "Maçazinha gostosinha nham nham", 4, 5);
=======
    (20.0, 'Melancia gostosinha nham nham', 3, 4),
    (25.0, 'Sorvetinho gostosinho nham nham', 7, 3),
    (5.0, 'Queijinho gostosinho nham nham', 8, 2),
    (10.0, 'Salaminho gostosinho nham nham', 9, 2),
    (2.50, 'Maçazinha gostosinha nham nham', 4, 3);
>>>>>>> Stashed changes


INSERT INTO
    address (street, city, state, country, postal_code)
VALUES
    ('Av. das Nações Unidas', 'Osasco', 'SP', 'Brasil', 06233200),
    ('Rod. José Carlos Daux', 'Florianópolis', 'SC', 'Brasil', 88032005);


INSERT INTO
    warehouse (name, address_id)
VALUES
    ('Armazem Melicidade', 1),
    ('Armazem Floripa', 2);


INSERT INTO
    section (description, name, temperature, volume, product_type_id, representative_id, warehouse_id)
values
    ('Setor de Produtos Fresco do Tipo Fruta', 'SETOR01-SP', 15.0, 500, 1, 1, 1),
    ('Setor de Produtos Fresco', 'SETOR02-SP', 18.0, 500, 1, 1, 1),
    ('Setor de Produtos Frios', 'SETOR03-SP', 10.0, 500, 2, 3, 1),
    ('Setor de Produtos Congelados', 'SETOR04-SP', 15.0, 500, 3, 3, 1),
    ('Setor de Produtos Fresco do Tipo Fruta', 'SETOR01-SC', 15.0, 500, 1, 2, 2),
    ('Setor de Produtos Fresco', 'SETOR02-SC', 18.0, 500, 1, 2, 2);


INSERT INTO purchase_status VALUES (1, 'APPROVED');
INSERT INTO purchase_status VALUES (2, 'PENDING');
INSERT INTO purchase_status VALUES (3, 'REJECTED');
INSERT INTO purchase_status VALUES (4, 'DELIVERED');