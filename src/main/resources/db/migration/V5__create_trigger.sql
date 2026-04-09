create table if not exists reservations_history as table reservations with no data;

create or replace function move_to_history()
    returns trigger as $$
begin
    insert into reservations_history (id, date, fk_passeio_id, fk_funcionario_id, fk_clientes_id, value, status)
    values (old.id, old.date, old.fk_passeio_id, old.fk_funcionario_id, old.fk_clientes_id, old.value, old.status);
    return old;
end;
$$ language plpgsql;

drop trigger if exists trg_move_history on reservations;

create trigger trg_move_history
    before delete on reservations
    for each row
execute function move_to_history();

delete from reservations where date < current_date;