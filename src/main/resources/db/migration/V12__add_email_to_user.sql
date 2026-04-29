ALTER TABLE user_account ADD COLUMN email varchar(100);
ALTER TABLE user_account ADD COLUMN reset_token varchar(6);
ALTER TABLE user_account ADD COLUMN reset_token_expiry timestamp;

UPDATE user_account SET email = 'admin@sgcf.local' WHERE user_name = 'admin';
UPDATE user_account SET email = 'funcionario@sgcf.local' WHERE user_name = 'funcionario';

ALTER TABLE user_account ALTER COLUMN email SET NOT NULL;
ALTER TABLE user_account ADD CONSTRAINT uq_user_email UNIQUE (email);
