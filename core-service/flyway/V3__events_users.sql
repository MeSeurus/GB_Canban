create table if not exists events_users
(
    event_id         bigserial,
    username         varchar(300) not null,
    primary key(event_id, username)
);
