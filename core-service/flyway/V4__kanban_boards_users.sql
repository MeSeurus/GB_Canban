create table if not exists kanban_boards_users
(
    kanban_board_id         bigserial,
    username         varchar(300) not null,
    primary key(kanban_board_id, username)
);
