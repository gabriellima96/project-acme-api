INSERT INTO order_request (requester, note, status) VALUES ('Gabriel Lima', null, 'PENDING');
INSERT INTO order_request (requester, note, status) VALUES ('João Lorenzo', null, 'PENDING');
INSERT INTO order_request (requester, note, status) VALUES ('Severino Enzo', null, 'PENDING');
INSERT INTO order_request (requester, note, status) VALUES ('Felipe dos Santos', 'Só teremos estoque no próximo mes', 'DENIED');
INSERT INTO order_request (requester, note, status) VALUES ('Felipe Miguel', 'Não estamos mais fornecendo este produto', 'DENIED');
INSERT INTO order_request (requester, note, status) VALUES ('Cláudio Brito', 'Produto sem estoque', 'DENIED');
INSERT INTO order_request (requester, note, status) VALUES ('Caio Anderson', null, 'APPROVED');
INSERT INTO order_request (requester, note, status) VALUES ('Diogo Lopes', null, 'APPROVED');
INSERT INTO order_request (requester, note, status) VALUES ('Daniel Nelson', null, 'APPROVED');

INSERT INTO product (order_request_id, description, price) VALUES (1, 'Motorola One', 1099.00);
INSERT INTO product (order_request_id, description, price) VALUES (2, 'Pilha AAA', 4.75);
INSERT INTO product (order_request_id, description, price) VALUES (3, 'Notebook Essentials', 2001.75);
INSERT INTO product (order_request_id, description, price) VALUES (4, 'Notebook Dell Inspiron', 3149.00);
INSERT INTO product (order_request_id, description, price) VALUES (5, 'Computador Desktop Completo', 1590.00);
INSERT INTO product (order_request_id, description, price) VALUES (6, 'Notebook Acer Aspire', 2749.99);
INSERT INTO product (order_request_id, description, price) VALUES (7, 'Notebook Expert X30', 2601.93);
INSERT INTO product (order_request_id, description, price) VALUES (8, 'TV LED 50 LG', 2199.00);
INSERT INTO product (order_request_id, description, price) VALUES (9, 'Pilha AA', 6.5);

