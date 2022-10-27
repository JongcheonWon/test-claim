INSERT INTO delivery_policy (delivery_fee_support_yn, one_way_delivery_fee, change_datetime, register_datetime)
VALUES ('Y', 2500, now(), now());

INSERT INTO delivery_policy (delivery_fee_support_yn, one_way_delivery_fee, change_datetime, register_datetime)
VALUES ('N', 2500, now(), now());

INSERT INTO purchase
(total_amount, product_amount, delivery_amount, delivery_policy_no, change_datetime, register_datetime)
VALUES(50500, 48000, 2500, 2, now(), now());

INSERT INTO order_product
(purchase_no, product_no, product_name, product_amount, product_quantity, change_datetime, register_datetime)
VALUES(1, 1, '신발A', 15000, 1, now(), now());

INSERT INTO order_product
(purchase_no, product_no, product_name, product_amount, product_quantity, change_datetime, register_datetime)
VALUES(1, 2, '신발B', 16000, 1, now(), now());

INSERT INTO order_product
(purchase_no, product_no, product_name, product_amount, product_quantity, change_datetime, register_datetime)
VALUES(1, 3, '신발C', 17000, 1, now(), now());

INSERT INTO purchase
(total_amount, product_amount, delivery_amount, delivery_policy_no, change_datetime, register_datetime)
VALUES(150000, 150000, 0, 1, now(), now());

INSERT INTO order_product
(purchase_no, product_no, product_name, product_amount, product_quantity, change_datetime, register_datetime)
VALUES(2, 4, '셔츠A', 40000, 1, now(), now());

INSERT INTO order_product
(purchase_no, product_no, product_name, product_amount, product_quantity, change_datetime, register_datetime)
VALUES(2, 5, '셔츠B', 50000, 1, now(), now());

INSERT INTO order_product
(purchase_no, product_no, product_name, product_amount, product_quantity, change_datetime, register_datetime)
VALUES(2, 6, '셔츠C', 60000, 1, now(), now());