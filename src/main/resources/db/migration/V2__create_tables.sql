create table if not exists user_account(
    id serial not null primary key,
    user_name varchar(35) unique not null,
    user_password varchar(35)  not null,
    permission varchar(30) not null
);

create table if not exists funcionario(
    id serial primary key not null,
    name varchar(50) not null,
    cpf varchar(11) not null
);

create table if not exists languages_funcionario(
    fk_funcionario_id bigint not null,
    language varchar(50),
    foreign key (fk_funcionario_id) references funcionario(id)
);

create table if not exists clientes(
    id serial not null primary key ,
    cnpj varchar(15) unique,
    cpf varchar(11) unique,
    name varchar(50) not null,
    country_of_customer varchar(50) not null
);

create table if not exists clientes_languages(
    fk_clientes_id bigint not null,
    language varchar(50),
    foreign key (fk_clientes_id) references clientes(id)
);

create table if not exists passeio(
    id serial not null primary key,
    price double precision not null,
    durations_in_minute bigint,
    country_of_tour varchar(50),
    km_of_tour bigint,
    name varchar(100) not null unique,
    locations varchar(255)
);

create table if not exists reservations(
    id serial not null primary key,
    date time not null,
    fk_passeio_id bigint not null,
    fk_funcionario_id bigint not null,
    fk_clientes_id bigint not null,
    value double precision not null,
    foreign key (fk_passeio_id) references passeio(id),
    foreign key (fk_funcionario_id) references funcionario(id),
    foreign key (fk_clientes_id) references clientes(id)
);

create table if not exists pay (
    id serial not null primary key,
    status varchar(15),
    fk_reservations_id bigint not null,
    foreign key (fk_reservations_id) references reservations(id)
);
