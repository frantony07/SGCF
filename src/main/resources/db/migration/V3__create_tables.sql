drop table if exists reservations cascade;

create table reservations(
    id serial not null primary key,
    date date not null,
    fk_passeio_id bigint not null,
    fk_funcionario_id bigint not null,
    fk_clientes_id bigint not null,
    value double precision not null,
    foreign key (fk_passeio_id) references passeio(id),
    foreign key (fk_funcionario_id) references funcionario(id),
    foreign key (fk_clientes_id) references clientes(id)
);

drop table if exists pay;

create table pay(
    id serial not null primary key,
    status varchar(15) not null,
    fk_reservation_id bigint not null,
    foreign key (fk_reservation_id) references reservations(id)
);