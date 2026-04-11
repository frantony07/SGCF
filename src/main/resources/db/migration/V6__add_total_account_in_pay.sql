alter table pay add column if not exists total_account float8 default 0;
alter table pay drop column if exists fk_reservation_id cascade;
alter table pay add column if not exists fk_cliente_id bigint unique;
alter table pay add constraint fk_pay_cliente foreign key (fk_cliente_id) references clientes(id);

create or replace function handle_customer_payment()
    returns trigger as $$
begin
    insert into pay (fk_cliente_id, total_account, status)
    values (new.fk_clientes_id, new.value, 'pendente')
    on conflict (fk_cliente_id)
        do update set
                      total_account = pay.total_account + excluded.total_account,
                      status = 'pendente';
    return new;
end;
$$ language plpgsql;

drop trigger if exists tr_after_reservation_insert on reservations;

create trigger tr_after_reservation_insert
    after insert on reservations
    for each row
execute function handle_customer_payment();