CREATE TABLE IF NOT EXISTS meta_funcionario (
    id serial not null,
    start_date date not null,
    end_date date not null,
    target_value double precision not null,
    fk_funcionario_id bigint,

    foreign key (fk_funcionario_id) references funcionario(id)
);