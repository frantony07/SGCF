CREATE OR REPLACE FUNCTION handle_customer_payment()
    RETURNS TRIGGER AS $$
BEGIN
INSERT INTO pay (fk_cliente_id, total_account, status)
VALUES (NEW.fk_clientes_id, NEW.value, 'pendente')
    ON CONFLICT (fk_cliente_id)
        DO UPDATE SET
    total_account = pay.total_account + EXCLUDED.total_account,
                   status = 'pendente';
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS tr_after_reservation_insert ON reservations;
DROP TRIGGER IF EXISTS tr_after_reservation_confirmed ON reservations;

CREATE TRIGGER tr_after_reservation_insert
    AFTER INSERT ON reservations
    FOR EACH ROW
    EXECUTE FUNCTION handle_customer_payment();