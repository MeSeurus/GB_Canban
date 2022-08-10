create table if not exists events_users
(
    id              bigserial primary key,
    username        varchar(60)
);