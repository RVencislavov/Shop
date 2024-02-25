CREATE TABLE Orders
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    cartId     INT,
    totalPrice DECIMAL(6, 2) NOT NULL,
    FOREIGN KEY (cartId) REFERENCES ShoppingCart (id)
);
