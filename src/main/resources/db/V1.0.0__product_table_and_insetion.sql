CREATE TABLE Products
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255)                                                 NOT NULL,
    price DECIMAL(5, 2)                                                NOT NULL,
    deal  ENUM ('NONE', 'TWO_FOR_THREE', 'BUY_ONE_GET_ONE_HALF_PRICE') NOT NULL DEFAULT 'NONE'
);

INSERT INTO Products (name, price, deal)
VALUES ('apple', 0.50, 'TWO_FOR_THREE'),
       ('banana', 0.40, 'TWO_FOR_THREE'),
       ('tomato', 0.30, 'TWO_FOR_THREE'),
       ('potato', 0.26, 'BUY_ONE_GET_ONE_HALF_PRICE');
