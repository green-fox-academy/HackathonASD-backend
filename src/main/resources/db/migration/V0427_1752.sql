alter table items add quantity bigint;
alter table items add discounted_price bigint;
alter table items add discount_rate bigint;
alter table items add discounted bool;

INSERT INTO items(name,department,description,link_to_image,cost,quantity,discounted_price,discount_rate,discounted)
VALUES('exampleItemShirt','formal','these are the best shirts ever', 'google.com', 25,15,20,20,true);

UPDATE items
SET
    quantity = 1,
    discounted_price = 25,
    discount_rate = 0,
    discounted = false
WHERE
        name = 'exampleItemShoes';

UPDATE items
SET
    quantity = 20,
    discounted_price = 40,
    discount_rate = 0,
    discounted = false
WHERE
        name = 'exampleItemWatch';

