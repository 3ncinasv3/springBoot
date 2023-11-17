CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description VARCHAR(1000),
    imageUrl VARCHAR(1000)
);

CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
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

CREATE TABLE starWars (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      isbn VARCHAR(255) NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      price DECIMAL(10,2) NOT NULL,
                      description VARCHAR(1000),
                      imageUrl VARCHAR(1000)
);

