ALTER TABLE customer_note ADD COLUMN create_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE customer_note ADD COLUMN title VARCHAR(50) NOT NULL;
