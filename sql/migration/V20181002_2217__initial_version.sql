
CREATE TYPE CUSTOMER_STATUS AS ENUM ('PROSPECTIVE', 'ACTIVE', 'DISABLED');

CREATE TABLE customer (
    id               BIGSERIAL PRIMARY KEY,
    create_timestamp TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status           CUSTOMER_STATUS      NOT NULL,
    name             VARCHAR(50)          NOT NULL,
    phone            VARCHAR(200)         DEFAULT NULL,
    email            VARCHAR(200)         NOT NULL UNIQUE
);

CREATE TABLE customer_note (
  id               BIGSERIAL PRIMARY KEY,
  customer_id      BIGINT REFERENCES customer (id) ON DELETE CASCADE,
  text             VARCHAR(200)     NOT NULL
);


