-- hardcoded users

INSERT INTO sec_user (email, firstName, encryptedPassword, enabled)
VALUES ('encinasv@sheridancollege.ca', 'Josh', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

INSERT INTO sec_user (email, firstName, encryptedPassword, enabled)
VALUES ('frank@sheridancollege.ca', 'Frank', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

INSERT INTO sec_user (email, firstName, encryptedPassword, enabled)
VALUES ('admin@joshuasons.ca', 'Administrator','$2y$10$reLJFH2owZU1Pcahs0Dgqu5SNiAUFpeTSgTcjYzTFUcU6mtxx6Swq', 3);

-- hardcoded roles
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_GUEST');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');

-- assigning roles

INSERT INTO user_role (userId, roleId)
VALUES (1, 1);

INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

INSERT INTO user_role (userId, roleId)
VALUES (3, 3);
