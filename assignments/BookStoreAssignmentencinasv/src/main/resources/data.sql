-- harry potter book table insertions
INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('123456789', 'Harry Potter and the Philosophers Stoned', 'J.K. Rowling', 20.99, 'First Harry Potter Book', 'https://th.bing.com/th?id=OIP.FjTMVQ1fJysWMxjDqtAsBQHaLH&w=204&h=306&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('167425395', 'Prisoner of Azkaban', 'J.K Rowling', 24.99, 'Third book of the series', 'https://th.bing.com/th?id=OIP.fK1MpsWTHX6LNtuprt5nnAHaLM&w=203&h=307&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('146263734', 'Goblet of Fire', 'J.K Rowling', 24.99, 'Fourth book of the series', 'https://th.bing.com/th?id=OIP.eUyHabvrwIihEIDN11basAHaLH&w=204&h=306&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('130171004', 'Half-Blood Prince', 'J.K Rowling', 29.99, 'Sixth book of the series', 'https://th.bing.com/th?id=OIP.Nbv_uJGmOe2l30HNN1tb2gHaLK&w=203&h=306&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('172729207', 'Order of the Phoenix', 'J.K Rowling', 29.99, 'Fifth book of the series', 'https://th.bing.com/th?id=OIP.FXct3x1OmTbAAR9zHrL0OgHaLH&w=204&h=306&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO book (isbn, title, author, price, description, imageUrl)
VALUES ('187654322', 'Deathly Hallows', 'J.K Rowling', 29.99, 'Seventh book of the series', 'https://th.bing.com/th?id=OIP.41V2lWTAHW66WgkpS7EfvwHaLM&w=203&h=307&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

-- hardcoded users

INSERT INTO sec_user (email, firstName, encryptedPassword, enabled)
VALUES ('encinasv@sheridancollege.ca', 'Josh', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO sec_user (email, firstName, encryptedPassword, enabled)
VALUES ('frank@sheridancollege.ca', 'Frank', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

-- hardcoded roles
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');

-- assigning roles

INSERT INTO user_role (userId, roleId)
VALUES (1, 1);

INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

-- star wars book table insert

INSERT INTO gameOfThrones (isbn, title, author, price, description, imageUrl)
VALUES ('947882369', 'A Game of Thrones', 'George R.R. Martin', 20.99, 'First Book of the series', 'https://th.bing.com/th?id=OIP.pFsASuBeDsUE_2Kzc2EGOgHaLU&w=202&h=309&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO gameOfThrones (isbn, title, author, price, description, imageUrl)
VALUES ('914694266', 'A Clash of Kings', 'George R.R. Martin', 24.99, 'Second book of the series', 'https://th.bing.com/th/id/OIP.PnbQfxmYELsKxeniySqn5wHaME?w=195&h=319&c=7&r=0&o=5&dpr=1.3&pid=1.7');

INSERT INTO gameOfThrones (isbn, title, author, price, description, imageUrl)
VALUES ('962247308', 'A Storm of Swords', 'George R.R. Martin', 24.99, 'Third book of the series', 'https://th.bing.com/th?id=OIP.pHJ_d6YrZIjGob9GRnwFjwHaL4&w=197&h=316&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO gameOfThrones (isbn, title, author, price, description, imageUrl)
VALUES ('986109382', 'A Feast for Crows', 'George R.R. Martin', 29.99, 'Fourth book of the series', 'https://th.bing.com/th?id=OIP.6nTALs6TjkHre8bvxROXQwHaMU&w=193&h=322&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO gameOfThrones (isbn, title, author, price, description, imageUrl)
VALUES ('980475533', 'A Dance with Dragons', 'George R.R. Martin', 29.99, 'Fifth book of the series', 'https://th.bing.com/th?id=OIP.wLaBwezt4oOPaAxQxk7kmAHaLX&w=201&h=309&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');

INSERT INTO gameOfThrones (isbn, title, author, price, description, imageUrl)
VALUES ('937749584', 'Fire & Blood', 'George R.R. Martin', 29.99, 'Sixth book of the series', 'https://th.bing.com/th?id=OIP.c2dLT7g98u0_jDHTgDAisAHaLY&w=201&h=309&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2');


