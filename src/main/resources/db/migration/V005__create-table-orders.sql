CREATE TABLE orders(
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id CHAR(36) NOT NULL,
    client_id CHAR(36),
    date DATE NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT ORDERS_FK_USER FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT ORDERS_FK_CLIENT FOREIGN KEY(client_id) REFERENCES clients(id)
);
