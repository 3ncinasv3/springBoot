
-- Inserting a book with ISBN '123456789', book name 'The Great Gatsby', serial name 'Serial1', author 'F. Scott Fitzgerald', and category 'Classic'
INSERT INTO book (isbn, bookName, serialName, authorName, category) VALUES ('123456789', 'The Great Gatsby', 'Serial1', 'F. Scott Fitzgerald', 'Classic');

-- Inserting another book with ISBN '987654321', book name 'To Kill a Mockingbird', serial name 'Serial2', author 'Harper Lee', and category 'Fiction'
INSERT INTO book (isbn, bookName, serialName, authorName, category) VALUES ('987654321', 'To Kill a Mockingbird', 'Serial2', 'Harper Lee', 'Fiction');

-- Inserting one more book with ISBN '456789123', book name 'The Hobbit', serial name 'Serial3', author 'J.R.R. Tolkien', and category 'Fantasy'
INSERT INTO book (isbn, bookName, serialName, authorName, category) VALUES ('456789123', 'The Hobbit', 'Serial3', 'J.R.R. Tolkien', 'Fantasy');


INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('fahad.jan@sheridancollege.ca', '$2y$10$o.zOTLi8Oemcja/ymmlIYeZ5TBeCjLIBeYPGCib3ADoU8EYgh6dNm', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('frank@sheridancollege.ca', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('iur@gmail.com', '$2y$10$Z5gPxl0pljh7NZDq/WYQH.i3nHv1uvg1fdch76UhmYZ2dKpU.0jWK', 1);

 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');
 
 
INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);


INSERT INTO user_role (userId, roleId)
VALUES (3, 1);