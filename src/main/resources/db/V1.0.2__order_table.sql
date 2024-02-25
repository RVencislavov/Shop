CREATE TABLE orders
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    cart_id     INT,
    total_price DECIMAL(6, 2) NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES shopping_cart (id)
);
