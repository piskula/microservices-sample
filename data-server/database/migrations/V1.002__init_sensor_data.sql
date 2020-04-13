INSERT INTO sensor(name) VALUES ('my_1');
INSERT INTO sensor(name) VALUES ('my_2');

INSERT INTO sensor_value(sensor_id, timestamp, value) SELECT id, localtimestamp, 22.375 FROM sensor WHERE name = 'my_1';
INSERT INTO sensor_value(sensor_id, timestamp, value) SELECT id, localtimestamp, 21.125 FROM sensor WHERE name = 'my_2';
