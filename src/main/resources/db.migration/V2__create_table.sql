create table if not exists funcionario(
    id serial primary key not null,
    name varchar(50) not null,
    cpf varchar(11) not null,

)