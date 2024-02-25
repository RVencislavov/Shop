CREATE TABLE ShoppingCartItems
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    productId INT,
    cartId    INT,
    quantity  INT NOT NULL,
    dealType  VARCHAR(25),
    FOREIGN KEY (productId) REFERENCES Products (id),
    FOREIGN KEY (cartId) REFERENCES ShoppingCart (id)
);
