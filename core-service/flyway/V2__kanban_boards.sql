create table if not exists kanban_boards
(
    id              bigserial primary key,
    name            varchar(60) not null,
    created_by      varchar(300) not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table if not exists kanban_boards_users
(
    kanban_board_id         bigserial,
    username         varchar(300) not null,
    primary key(kanban_board_id, username)
    );

insert into kanban_boards_users (kanban_board_id, username)
values  ('1', 'user1'),
        ('1', 'user2'),
        ('1', 'user3'),
        ('2', 'user1');
