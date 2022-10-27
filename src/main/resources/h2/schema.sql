DROP TABLE IF EXISTS purchase CASCADE;
CREATE TABLE purchase
(
    purchase_no        bigint   NOT NULL AUTO_INCREMENT,
    total_amount       int DEFAULT 0,
    product_amount     int DEFAULT 0,
    delivery_amount    int DEFAULT 0,
    delivery_policy_no bigint   NOT NULL,
    change_datetime    datetime not null,
    register_datetime  datetime not null,
    PRIMARY KEY (purchase_no)
);

DROP TABLE IF EXISTS order_product CASCADE;
CREATE TABLE order_product
(
    order_no         bigint       NOT NULL AUTO_INCREMENT,
    purchase_no      bigint       NOT NULL,
    product_no       bigint       NOT NULL,
    product_name     varchar(100) NOT NULL,
    product_amount   int DEFAULT 0,
    product_quantity int          NOT NULL,
    change_datetime    datetime not null,
    register_datetime  datetime not null,
    PRIMARY KEY (order_no)
);

CREATE INDEX idx_order_product_01 ON order_product (purchase_no);


DROP TABLE IF EXISTS delivery_policy CASCADE;
CREATE TABLE delivery_policy
(
    delivery_policy_no      bigint   NOT NULL AUTO_INCREMENT,
    delivery_fee_support_yn char(1)  NOT NULL,
    one_way_delivery_fee    int DEFAULT 0,
    change_datetime    datetime not null,
    register_datetime  datetime not null,
    PRIMARY KEY (delivery_policy_no)
);

DROP TABLE IF EXISTS claim CASCADE;
CREATE TABLE claim
(
    claim_no    bigint   NOT NULL AUTO_INCREMENT,
    purchase_no bigint   NOT NULL,
    claim_type  char(1)  NOT NULL,
    change_datetime    datetime not null,
    register_datetime  datetime not null,
    PRIMARY KEY (claim_no)
);

CREATE INDEX idx_claim_01 ON claim (purchase_no);


DROP TABLE IF EXISTS claim_product CASCADE;
CREATE TABLE claim_product
(
    claim_product_no bigint   NOT NULL AUTO_INCREMENT,
    claim_no         bigint   NOT NULL,
    product_no       bigint   NOT NULL,
    product_amount   int DEFAULT 0,
    product_quantity int      NOT NULL,
    change_datetime    datetime not null,
    register_datetime  datetime not null,
    PRIMARY KEY (claim_product_no)
);

CREATE INDEX idx_claim_product_01 ON claim_product (claim_no);

DROP TABLE IF EXISTS claim_refund CASCADE;
CREATE TABLE claim_refund
(
    claim_refund_no    bigint   NOT NULL AUTO_INCREMENT,
    claim_no           bigint   NOT NULL,
    claim_delivery_fee int      NOT NULL,
    change_datetime    datetime not null,
    register_datetime  datetime not null,
    PRIMARY KEY (claim_refund_no)
);

CREATE INDEX idx_claim_refund_01 ON claim_refund (claim_no);