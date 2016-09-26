DELETE FROM apple;
DELETE FROM computer;
DELETE FROM play;

INSERT INTO apple (id, color, manufacturer, price, date_of_harvesting) VALUES (1, 'red', 'Sunny Meadow', 10, '2016-09-01');
INSERT INTO apple (id, color, manufacturer, price, date_of_harvesting) VALUES (2, 'yellow', 'Funny Farm', 7, '2016-09-05');
INSERT INTO apple (id, color, manufacturer, price, date_of_harvesting) VALUES (3, 'green', 'Forest Edge', 6, '2016-09-10');
INSERT INTO apple (id, color, manufacturer, price, date_of_harvesting) VALUES (4, 'red', 'Blooming Fields', 5, '2016-09-15');
INSERT INTO apple (id, color, manufacturer, price, date_of_harvesting) VALUES (5, 'yellow', 'Summer Cottage', 3, '2016-09-20');
INSERT INTO apple (id, color, manufacturer, price, date_of_harvesting) VALUES (6, 'green', 'Snow Mountains', 2, '2016-09-25');

INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (1, 'Sony', 2, '2015-12-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (2, 'Sony', 4, '2016-01-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (3, 'Sony', 8, '2016-02-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (4, 'HP', 2, '2016-03-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (5, 'HP', 4, '2016-04-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (6, 'HP', 8, '2016-05-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (7, 'Apple', 2, '2016-06-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (8, 'Apple', 4, '2016-07-01');
INSERT INTO computer (id, manufacturer, cores_number, date_of_manufacture) VALUES (9, 'Apple', 8, '2016-09-01');

INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'The Phantom of the Opera', 'Boston Opera House', '2016-10-01 2:00');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Cats', 'Palau de la Musica Catalana', '2016-11-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Chicago', 'Sydney Opera House', '2016-12-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'The Lion King', 'Palacio de Bellas Artes', '2017-01-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Les Miserables', 'Odeon of Herodes Atticus', '2017-02-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Oh! Calcutta!', 'Royal Albert Hall', '2017-03-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'A Chorus Line', 'Teatro Amazonas', '2017-04-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Beauty and the Beast', 'Radio City Music Hall', '2017-05-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Mamma Mia!', 'Palais Garnier', '2017-04-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Rent', 'Teatro Solis', '2017-03-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Wicked', 'Les Celestins', '2017-02-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Miss Saigon', 'Chicago Theatre', '2017-01-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Jersey Boys', 'TCL Chinese Theatre', '2016-12-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, '42nd Street', 'Elgin and Winter Garden Theatre Centre', '2016-11-01');
INSERT INTO play (id, name, theatre, timestamp) VALUES (1, 'Grease', 'Minack Theatre', '2016-10-01');