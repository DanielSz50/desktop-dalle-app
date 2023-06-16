CREATE TABLE IF NOT EXISTS images (
    id          SERIAL PRIMARY KEY,
    filepath    VARCHAR NOT NULL,
    title       VARCHAR NULL,
    query       VARCHAR NOT NULL,
    created     TIMESTAMP NOT NULL,
    updated     TIMESTAMP NOT NULL
);
