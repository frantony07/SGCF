drop table if exists bonus_funcionario;

create table bonus_funcionario(
                                  fk_reservations_id bigint not null,
                                  value float8,
                                  date date not null,
                                  constraint fk_bonus_reservations
                                      foreign key (fk_reservations_id)
                                          references reservations (id)
                                          on delete cascade
);

create or replace function add_bonus_payment_in_employee()
    returns trigger as $$
begin
    insert into bonus_funcionario (fk_reservations_id, value, date)
    values (new.id, new.value * 0.2, new.date);
    return new;
end;
$$ language plpgsql;

drop trigger if exists after_reservation_insert on reservations;

create trigger after_reservation_insert
    after insert on reservations
    for each row
execute function add_bonus_payment_in_employee();