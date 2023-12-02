CREATE TABLE student (
    id LONG PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    lastName VARCHAR(255),
    grade INTEGER,
    letterGrade VARCHAR(2),
    attended BIT,
    participated BIT
);




INSERT INTO student (name, lastName, grade, letterGrade, attended, participated) VALUES
    ('Josh', 'Valador', '100', 'A+', '1', '1');