create table if not exists kanban_boards
(
    id              bigserial primary key,            
    title           varchar(60) not null,
    created_by      varchar(300) not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into kanban_boards (id, title, created_by)
values  (1, 'My Asana', 'user1'),
        (2, 'My Jira', 'user2'),
        (3, 'My Canban', 'user3'),
        (4, 'My Asana2', 'user1');

create table if not exists kanban_boards_users
(
    kanban_board_id         bigserial,
    username_added          varchar(300) not null,
    primary key(kanban_board_id, username_added)
);

insert into kanban_boards_users (kanban_board_id, username_added)
values  ('1', 'user1'),
        ('1', 'user2'),
        ('1', 'user3'),
        ('3', 'user3'),
        ('4', 'user1'),
        ('2', 'user1');
