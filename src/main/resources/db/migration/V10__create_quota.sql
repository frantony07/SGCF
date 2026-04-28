CREATE TABLE IF NOT EXISTS meta_funcionario (
    id serial not null,
    start_date date not null,
    end_date date,
    target_value double precision not null,
    accumulated_value double precision not null,
    fk_reservation_id serial not null,
    fk_funcionario_id serial,

    foreign key (fk_funcionario_id) references funcionarios(id)
);