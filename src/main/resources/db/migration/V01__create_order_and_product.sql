CREATE TABLE order_request  (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    requester VARCHAR(120) NOT NULL,
    note VARCHAR(255),
    status ENUM('APPROVED', 'DENIED', 'PENDING') NOT NULL DEFAULT 'PENDING'
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE product (
    order_request_id BIGINT(20) NOT NULL,
    description VARCHAR(60) NOT NULL,
    price DECIMAL(11, 2) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE product
ADD CONSTRAINT product_order_request_fk
    FOREIGN KEY (order_request_id)
        REFERENCES order_request (id);
