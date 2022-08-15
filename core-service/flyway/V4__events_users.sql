create table if not exists events_users
(
    event_id              bigserial primary key,
    username_added        varchar(60)
);