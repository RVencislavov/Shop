CREATE TABLE shoppingCartItems
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    productId INT,
    cartId    INT,
    quantity  INT NOT NULL,
    dealType  VARCHAR(25),
    FOREIGN KEY (productId) REFERENCES products (id),
    FOREIGN KEY (cartId) REFERENCES shoppingCart (id)
);
