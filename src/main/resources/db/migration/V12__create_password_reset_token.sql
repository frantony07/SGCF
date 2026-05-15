CREATE TABLE password_reset_token (
     id SERIAL PRIMARY KEY,
     token VARCHAR(10) NOT NULL,
     expiration TIMESTAMP NOT NULL,
     used BOOLEAN NOT NULL,
     user_id BIGINT NOT NULL,
     CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES user_account(id)
);