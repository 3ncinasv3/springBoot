--INSERT INTO book (title, author, price, description) VALUES
--('The Great Gatsby', 'F. Scott Fitgerald', 19.99, 'A classic American novel set in the Roaring Twenties, exploring themes of wealth, excess, and the American Dream'),
--('To Kill a Mockingbird', 'Harper Lee', 29.99, 'A powerful novel addressing issues of racial injustice, moral growth, and compassion in the American South during the 1930s'),
--('1984', 'George Orwell', 39.99, 'A dystopian novel depicting a totalitarian society, surveillance, and the struggle for truth and individuality.');

--INSERT INTO person (username, password, email) VALUES
--('user1', '123!@#', 'user1@example.com'),
--('user2', '456$%^', 'user2@example.com');

INSERT INTO books (isbn, title, author, price, description) VALUES
(1, 'The Great Gatsby', 'F. Scott Fitzgerald', 19.99, 'A classic American novel set in the Roaring Twenties, exploring themes of wealth, excess, and the American Dream'),
(2, 'To Kill a Mockingbird', 'Harper Lee', 29.99, 'A powerful novel addressing issues of racial injustice, moral growth, and compassion in the American South during the 1930s'),
(3, '1984', 'George Orwell', 39.99, 'A dystopian novel depicting a totalitarian society, surveillance, and the struggle for truth and individuality');

INSERT INTO sec_user (userName, email, encryptedPassword, enabled) VALUES
('user1', 'user1@example.com', '$2a$12$SomeHashValueHere', 1),
('user2', 'user2@example.com', '$2a$12$AnotherHashValueHere', 1);

INSERT INTO sec_role (roleId, roleName) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_GUEST');

INSERT INTO user_role (id, roleId) VALUES
(1, 1);
INSERT INTO user_role (id, roleId) VALUES
(2, 2);
