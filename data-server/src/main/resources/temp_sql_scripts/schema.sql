CREATE TABLE sensor
(
    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE sensor_value
(
    id         SERIAL NOT NULL,
    sensor_id  SERIAL NOT NULL REFERENCES sensor(id),
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL, -- DEFAULT localtimestamp
    value      DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
);
