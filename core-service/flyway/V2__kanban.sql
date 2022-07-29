create table if not exists kanban_boards
(
    id              bigserial primary key,
    name            varchar(60) not null,
    creator         varchar(300) not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
