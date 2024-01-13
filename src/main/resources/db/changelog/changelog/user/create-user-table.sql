create table "user"
(
    id           uuid primary key,
    name         varchar,
    surname      varchar,
    phone_number varchar not null unique,
    password     varchar not null,
    birth_date   timestamp,
    created      timestamp,
    updated      timestamp
)