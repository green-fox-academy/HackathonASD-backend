DROP TABLE IF EXISTS wishlist_item;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS wishlists;





CREATE TABLE wishlists
(
    wishlist_id bigint not null auto_increment,
    wishlist_name        varchar(255),
    PRIMARY KEY (wishlist_id)
);
INSERT INTO wishlists (wishlist_id, wishlist_name)
VALUES (1, 'Bond\'s wishlist');




CREATE TABLE items
(
    id            bigint not null auto_increment,
    name          varchar(50),
    department    varchar(50),
    description   varchar(255),
    link_to_image varchar(255),
    cost          bigint,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id          bigint not null auto_increment,
    username    varchar(30),
    password    varchar(255),
    wishlist_id bigint(20),
    PRIMARY KEY (id),
    CONSTRAINT users_wishlists_id FOREIGN KEY (wishlist_id) REFERENCES wishlists (wishlist_id)
);

CREATE TABLE orders
(
    id      bigint not null auto_increment,
    user_id bigint not null,
    address varchar (255),
    FOREIGN KEY (user_id) REFERENCES users (id),
    PRIMARY KEY (id)
);


CREATE TABLE order_item
(
    id       bigint not null auto_increment,
    order_id bigint not null,
    item_id  bigint not null,
    PRIMARY KEY (id),
    CONSTRAINT order_item_ibfk_1
        FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT order_item_ibfk_2
        FOREIGN KEY (item_id) REFERENCES items (id)
);

CREATE TABLE wishlist_item
(
    id bigint not null auto_increment,
    wishlist_id bigint not null,
    item_id bigint not null,
    PRIMARY KEY (id) ,
    CONSTRAINT wishlist_item_ibfk_10
        FOREIGN KEY (wishlist_id) REFERENCES wishlists (wishlist_id),
    CONSTRAINT wishlist_item_ibfk_20
        FOREIGN KEY (item_id) REFERENCES items (id)
);

INSERT INTO users (username, password,wishlist_id)
VALUES ('Bond', 'password',1);
INSERT INTO users (username, password,wishlist_id)
VALUES ('James', 'password',2);


INSERT INTO items(name, department, description, link_to_image, cost)
VALUES ('exampleItemShoes', 'sport', 'these are the best running shoes ever', 'google.com', 25);
INSERT INTO items(name, department, description, link_to_image, cost)
VALUES ('exampleItemWatch', 'sport', 'watch out! this is the best watch ever', 'ncore.pro', 40);