CREATE TABLE shopping_cart_items
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    cart_id    INT,
    quantity  INT NOT NULL,
    deal_type  VARCHAR(30),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (cart_id) REFERENCES shopping_cart (id)
);
