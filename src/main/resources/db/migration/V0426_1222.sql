DROP TABLE IF EXISTS items;

CREATE TABLE items
(
    id          bigint not null auto_increment,
    name        varchar(50),
    department       varchar(50),
    description varchar(255),
    link_to_image varchar(255),
    cost        bigint,
    PRIMARY KEY (id)
);

INSERT INTO items(name,department,description,link_to_image,cost)
VALUES('exampleItemShoes','sport','these are the best running shoes ever', 'exampleURL', 25);
INSERT INTO items(name,department,description,link_to_image,cost)
VALUES('exampleItemWatch','sport','watch out! this is the best watch ever', 'exampleWatchURL', 40);