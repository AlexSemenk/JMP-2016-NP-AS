DELETE FROM apple;
DELETE FROM computer;
DELETE FROM play;

INSERT INTO task2_dst.apple (id, color, manufacturer, price, date_of_harvesting)
SELECT id, color, manufacturer, price, date_of_harvesting FROM task2_src.apple;

INSERT INTO task2_dst.computer (id, manufacturer, cores_number, date_of_manufacture) 
SELECT id, manufacturer, cores_number, date_of_manufacture FROM task2_src.computer;

INSERT INTO task2_dst.play (id, name, theatre, timestamp)
SELECT id, name, theatre, timestamp FROM task2_src.play;