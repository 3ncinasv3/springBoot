CREATE TABLE book (
    isbn INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    price DOUBLE,
    description VARCHAR(500)
);
--
--CREATE TABLE person (
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    username VARCHAR(50),
--    password VARCHAR(255), 
--    email VARCHAR(255)
--);

--CREATE TABLE book (
--    isbn INT AUTO_INCREMENT PRIMARY KEY,
--    title VARCHAR(100),
--    author VARCHAR(100),
--    price DECIMAL(10, 2),
--    description VARCHAR(500)
--);

CREATE TABLE sec_user (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(75) NOT NULL UNIQUE,
    email VARCHAR(75) NOT NULL UNIQUE,
    encryptedPassword VARCHAR(128) NOT NULL,
    enabled BIT NOT NULL
);

CREATE TABLE sec_role (
    roleId INT AUTO_INCREMENT PRIMARY KEY,
    roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    roleId INT NOT NULL,
    CONSTRAINT user_role_uk UNIQUE (userId, roleId),
    CONSTRAINT user_role_fk1 FOREIGN KEY (userId) REFERENCES sec_user (userId),
    CONSTRAINT user_role_fk2 FOREIGN KEY (roleId) REFERENCES sec_role (roleId)
);
