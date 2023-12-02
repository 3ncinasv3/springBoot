CREATE TABLE book(
id LONG PRIMARY KEY AUTO_INCREMENT,
isbn VARCHAR(255),
bookName VARCHAR(255),
serialName VARCHAR(255),
authorName VARCHAR(255),
category VARCHAR(255)
);

CREATE TABLE order_table (
   order_id SERIAL PRIMARY KEY,
   user_id BIGINT,
   book_id BIGINT,
   quantity INT,
   order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE sec_user (
  userId            BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email             VARCHAR(75) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  enabled           BIT NOT NULL 
);

CREATE TABLE sec_role(
  roleId   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
  id     BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_uk UNIQUE (userId, roleId);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk1 FOREIGN KEY (userId)
  REFERENCES sec_user (userId);
 
ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk2 FOREIGN KEY (roleId)
  REFERENCES sec_role (roleId);
