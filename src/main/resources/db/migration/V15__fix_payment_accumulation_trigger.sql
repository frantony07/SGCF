CREATE OR REPLACE FUNCTION handle_customer_payment()
    RETURNS TRIGGER AS $$
BEGIN
INSERT INTO pay (fk_cliente_id, total_account, status)
VALUES (NEW.fk_clientes_id, NEW.value, 'pendente')
    ON CONFLICT (fk_cliente_id)
        DO UPDATE SET
    total_account = CASE
                   WHEN pay.status = 'CONFIRMADA' THEN EXCLUDED.total_account
                   ELSE pay.total_account + EXCLUDED.total_account
END,
            status = 'pendente';
RETURN NEW;
END;
$$ LANGUAGE plpgsql;