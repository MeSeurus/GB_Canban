create table if not exists events_users (
    event_id         bigserial primary key,
    username         varchar(300) not null,
    primary key(event_id, username)
);
