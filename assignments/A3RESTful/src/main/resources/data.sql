-- Initial components data
INSERT INTO cpu (model, manufacturer, price, description, speedGHz)
VALUES ('Ryzen 9 7950x', 'AMD', 749.00, '16 core 32 thread processor built on the 5nm process and is compatible with AM5 motherboards.', 4.5),
       ('Core i9 14900K', 'Intel', 450.00, 'Description for CPU 2', 4.0);

-- Insert Graphics Cards
INSERT INTO graphics_card (model, manufacturer, price, description, vramGB)
VALUES ('GPU Model 1', 'GPU Manufacturer X', 500.00, 'Your ma', 8),
       ('GPU Model 2', 'GPU Manufacturer Y', 400.00, 'Your ma', 6);

-- Insert Memory
INSERT INTO memory (model, manufacturer, price, description, sizeGB, type)
VALUES ('Memory Model 1', 'Memory Manufacturer P', 100.00, 'Description for Memory Model 1', 8, 'DDR4'),
       ('Memory Model 2', 'Memory Manufacturer Q', 120.00, 'Description for Memory Model 2',  16, 'DDR4');

-- Insert Motherboards
INSERT INTO motherboard (model, manufacturer, price, description, socketType)
VALUES ('Motherboard Model 1', 'Manufacturer C', 200.00, 'This is description', 'Socket Type A'),
       ('Motherboard Model 2', 'Manufacturer D', 180.00, 'This is description', 'Socket Type B');

-- Insert Storage
INSERT INTO storage (model, manufacturer, price, description, capacityGB, type)
VALUES ('Storage Model 1', 'Manufacturer E', 80.00, 'This is a description', 500, 'SSD'),
       ('Storage Model 2', 'Manufacturer F', 60.00, 'This is a description', 1000, 'HDD');

-- Insert Roles
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER'),
       ('ROLE_GUEST'),
       ('ROLE_ADMIN');

-- Insert Users
INSERT INTO sec_user (userName, email, encryptedPassword, enabled)
VALUES ('user', 'user@example.com', '$2a$10$UVSvRwRCUdL2mSt.C2Eu0OFPuE1oqce8/fKyoQ79awZ/D6qnYCcNK', 1),
       ('guest', 'guest@example.com', '$2a$10$XVCJSurVwPHyDIJgO7zYgOXm35EPCVRNquqbwphlHYzXZyAaePFCS', 1),
       ('admin', 'admin@example.com', '$2a$10$UT5GtaFtHjK2tZLo4tUcMuTaHk7sXUFH3S46rS9xPLx3T.xD6PYLG', 1);

-- Assign Roles to Users
INSERT INTO user_role (userId, roleId)
VALUES (1, 1), -- user has ROLE_USER
       (2, 2), -- guest has ROLE_GUEST
       ((SELECT userId FROM sec_user WHERE userName = 'admin'), 3); -- admin has ROLE_ADMIN