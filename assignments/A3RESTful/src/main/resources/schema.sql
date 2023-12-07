-- Table for CPUs
CREATE TABLE cpu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255),
    manufacturer VARCHAR(255),
    price FLOAT,
    description VARCHAR(500),
    speedGHz FLOAT
);

-- Table for Graphics Cards
CREATE TABLE graphics_card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255),
    manufacturer VARCHAR(255),
    price FLOAT,
    description VARCHAR(255),
    vramGB INT
);

-- Table for Memory
CREATE TABLE memory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255),
    manufacturer VARCHAR(255),
    price FLOAT,
    sizeGB INT,
    description VARCHAR(255),
    type VARCHAR(100)
);

-- Table for Motherboard
CREATE TABLE motherboard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255),
    manufacturer VARCHAR(255),
    price FLOAT,
    description VARCHAR(255),
    socketType VARCHAR(100)
);

-- Table for Storage
CREATE TABLE storage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255),
    manufacturer VARCHAR(255),
    price FLOAT,
    description VARCHAR(255),
    capacityGB INT,
    type VARCHAR(100)
);

-- User-related tables
CREATE TABLE sec_user (
    userId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(50),
    email VARCHAR(75) NOT NULL UNIQUE,
    encryptedPassword VARCHAR(128) NOT NULL,
    enabled BIT NOT NULL
);

CREATE TABLE sec_role(
    roleId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
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