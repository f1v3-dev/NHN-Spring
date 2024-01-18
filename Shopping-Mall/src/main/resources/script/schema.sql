CREATE TABLE IF NOT EXISTS Categories
(
    category_id   INT auto_increment,
    category_name varchar(50) NOT NULL UNIQUE,

    CONSTRAINT pk_Categories PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS Products
(
    product_id    INT auto_increment,
    model_number  varchar(10)  NOT NULL UNIQUE,
    model_name    varchar(120) NOT NULL,
    product_image varchar(30),
    unit_cost     INT          NOT NULL,
    description   text,

    CONSTRAINT pk_Products PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS Products_Categories
(
    product_id  INT,
    category_id INT,

    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES Products (product_id),
    FOREIGN KEY (category_id) REFERENCES Categories (category_id)
);
