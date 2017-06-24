drop table if exists orders;
CREATE TABLE orders
(id INT NOT NULL AUTO_INCREMENT,
 user_id INT NOT NULL,
 position_id INT NOT NULL,
 order_date TIMESTAMP,
 PRIMARY KEY (id));
INSERT into orders(user_id,position_id,order_date) VALUES (2,1,CURRENT_TIMESTAMP);
INSERT into orders(user_id,position_id,order_date) VALUES (1,2,CURRENT_TIMESTAMP);
INSERT into orders(user_id,position_id,order_date) VALUES (2,3,CURRENT_TIMESTAMP);
INSERT into orders(user_id,position_id,order_date) VALUES (1,1,CURRENT_TIMESTAMP);
INSERT into orders(user_id,position_id,order_date) VALUES (1,5,CURRENT_TIMESTAMP);
