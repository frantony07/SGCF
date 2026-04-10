CREATE TRIGGER trg_expire_reservations
    BEFORE UPDATE ON reservations
    FOR EACH ROW
EXECUTE FUNCTION fn_expire_reservations();

UPDATE reservations
SET status = status;

SELECT column_name
FROM information_schema.columns
WHERE table_name = 'reservations';

ALTER TABLE reservations
    ADD COLUMN status VARCHAR(20);

UPDATE reservations SET status = status;