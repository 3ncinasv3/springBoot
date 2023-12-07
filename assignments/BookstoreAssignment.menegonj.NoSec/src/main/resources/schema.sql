CREATE TABLE book (
    isbn INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    price DOUBLE,
    description VARCHAR(500)
);

CREATE TABLE person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(255), 
    email VARCHAR(255)
);