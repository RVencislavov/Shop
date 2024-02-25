CREATE TABLE products
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255)                                                 NOT NULL,
    price DECIMAL(5, 2)                                                NOT NULL,
    deal  ENUM ('NONE', 'TWO_FOR_THREE', 'BUY_ONE_GET_ONE_HALF_PRICE') NOT NULL DEFAULT 'NONE'
);
