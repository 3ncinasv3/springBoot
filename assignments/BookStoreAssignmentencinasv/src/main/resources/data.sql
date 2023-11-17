INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('123456789', 'Harry Potter and the Philosophers Stoned', 'J.K. Rowling', 20.99, 'First Harry Potter Book', 'https://news.artnet.com/app/news-upload/2018/08/Harry-Potter-and-the-Sorcerers-Stone-Book-Cover-1024x1473-256x256.jpg');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('987654321', 'Prisoner of Azkaban', 'J.K Rowling', 24.99, 'Third book of the series', 'https://play-lh.googleusercontent.com/I2Fj_ElbPHPkzUpxhBHZ3o1Qq_DKRI_Ex3_NXb0w7Ec37ugm4nPD_XJfSNzOZ8IP28sy8ZErqxJBTSo=s256-rw');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('192837465', 'Goblet of Fire', 'J.K Rowling', 24.99, 'Fourth book of the series', 'https://m.media-amazon.com/images/I/71HbFLlw9LL._UC256,256_CACC,256,256_.jpg');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('987654999', 'Half-Blood Prince', 'J.K Rowling', 29.99, 'Sixth book of the series', 'https://play-lh.googleusercontent.com/EatgxMET6OEwFTMuKQ6E8ih0DuFVccEIPuV2NywVog5COMUKSRVfrAJAD_qhLPlRXkFIAH-uGWXidw=s256-rw');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('987659211', 'Order of the Phoenix', 'J.K Rowling', 29.99, 'Fifth book of the series', 'https://m.media-amazon.com/images/I/716aIR-UdoL._UC256,256_CACC,256,256_.jpg');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('987654322', 'Deathly Hallows', 'J.K Rowling', 29.99, 'Seventh book of the series', 'https://static.truetrophies.com/boxart/Game_811.jpg');

INSERT INTO customer (username, password, email)
VALUES ('user1', 'password1', 'user1@example.com');

INSERT INTO customer (username, password, email)
VALUES ('user2', 'password2', 'user2@example.com');

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('fahad.jan@sheridancollege.ca', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('frank@sheridancollege.ca', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');

INSERT INTO user_role (userId, roleId)
VALUES (1, 1);

INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

INSERT INTO starWars (isbn, title, author, price, description, imageUrl)
VALUES ('123456789', 'Harry Potter and the Philosophers Stoned', 'J.K. Rowling', 20.99, 'First Harry Potter Book', 'https://news.artnet.com/app/news-upload/2018/08/Harry-Potter-and-the-Sorcerers-Stone-Book-Cover-1024x1473-256x256.jpg');

INSERT INTO starWars (isbn, title, author, price, description, imageUrl)
VALUES ('987654321', 'Prisoner of Azkaban', 'J.K Rowling', 24.99, 'Third book of the series', 'https://play-lh.googleusercontent.com/I2Fj_ElbPHPkzUpxhBHZ3o1Qq_DKRI_Ex3_NXb0w7Ec37ugm4nPD_XJfSNzOZ8IP28sy8ZErqxJBTSo=s256-rw');

INSERT INTO starWars (isbn, title, author, price, description, imageUrl)
VALUES ('192837465', 'Goblet of Fire', 'J.K Rowling', 24.99, 'Fourth book of the series', 'https://m.media-amazon.com/images/I/71HbFLlw9LL._UC256,256_CACC,256,256_.jpg');

INSERT INTO starWars (isbn, title, author, price, description, imageUrl)
VALUES ('987654999', 'Half-Blood Prince', 'J.K Rowling', 29.99, 'Sixth book of the series', 'https://play-lh.googleusercontent.com/EatgxMET6OEwFTMuKQ6E8ih0DuFVccEIPuV2NywVog5COMUKSRVfrAJAD_qhLPlRXkFIAH-uGWXidw=s256-rw');

INSERT INTO starWars (isbn, title, author, price, description, imageUrl)
VALUES ('987659211', 'Order of the Phoenix', 'J.K Rowling', 29.99, 'Fifth book of the series', 'https://m.media-amazon.com/images/I/716aIR-UdoL._UC256,256_CACC,256,256_.jpg');

INSERT INTO starWars (isbn, title, author, price, description, imageUrl)
VALUES ('987654322', 'Deathly Hallows', 'J.K Rowling', 29.99, 'Seventh book of the series', 'https://static.truetrophies.com/boxart/Game_811.jpg');


