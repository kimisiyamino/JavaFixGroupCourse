CREATE DATABASE fixgroup_db;

CREATE TABLE fix_user(
                         id SERIAL PRIMARY KEY NOT NULL,
                         firstName VARCHAR(30) NOT NULL,
                         lastName VARCHAR(30) NOT NULL
);

INSERT INTO fix_user(firstName, lastName) VALUES ('Leo', 'Naduty');
INSERT INTO fix_user(firstName, lastName) VALUES ('Tony', 'Nagashe');
INSERT INTO fix_user(firstName, lastName) VALUES ('Jonny', 'Batlov');

SELECT * FROM fix_user;

CREATE TABLE fix_car(
                        id SERIAL PRIMARY KEY NOT NULL,
                        model VARCHAR(30) NOT NULL,
                        ownerId INTEGER REFERENCES fix_user(id)
);

INSERT INTO fix_car(model, ownerId) VALUES ('Mercedes', 1);
INSERT INTO fix_car(model, ownerId) VALUES ('Mazda', 1);
INSERT INTO fix_car(model, ownerId) VALUES ('Mercedes', 2);
INSERT INTO fix_car(model, ownerId) VALUES ('Opel', 1);
INSERT INTO fix_car(model) VALUES ('Lada');

SELECT * FROM fix_car;

UPDATE fix_car SET ownerId=2 WHERE id=4;

SELECT * FROM fix_user LEFT JOIN fix_car ON fix_user.id = fix_car.ownerId;
SELECT * FROM fix_user RIGHT JOIN fix_car ON fix_user.id = fix_car.ownerId;
SELECT * FROM fix_user INNER JOIN fix_car ON fix_user.id = fix_car.ownerId;