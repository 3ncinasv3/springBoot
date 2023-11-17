CREATE TABLE appointment(
                            id LONG PRIMARY KEY AUTO_INCREMENT,
                            firstName VARCHAR(255),
                            email VARCHAR(255),
                            appointmentDate DATE,
                            appointmentTime TIME
);