create sequence HIBERNATE_SEQUENCE
    minvalue 100000
    maxvalue 9999999999999999
    start with 100060
    increment by 1
    cache 20;

create table if not exists events
(
    id              bigserial primary key,
    title           varchar(60) not null,
    content         varchar(300),
    username        varchar(60),
    begin_date      timestamp not null,
    end_date        timestamp not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);


insert into events (title, content, username, begin_date, end_date)
values ( 'Create simple program', 'Hello World!', 'user1', '2022-06-24 01:00:00', '2022-06-24 03:00:00'),
       ( 'Create hard program', 'Hello World2!!', 'user2', '2022-06-24 01:00:00', '2022-06-24 07:00:00'),
       ( 'Create SUPER hard program', 'Hello World3!!', 'user3', '2022-06-24 01:00:00', '2022-06-24 03:00:00'),
       ( 'Create SUPER hard program', 'Hello World4!!', 'user3', '2022-06-24 04:00:00', '2022-06-24 06:00:00'),
       ( 'Create hard program', 'Hello World4!!', 'user3', '2022-08-04 04:00:00', '2022-06-24 06:00:00'),
       ( 'Create SUPER hard program', 'Hello World4!!', 'user3', '2022-07-15 04:00:00', '2022-06-24 06:00:00'),
       ( 'Create SUPER hard program', 'Hello World5!!', 'user4', '2022-06-24 01:00:00','2022-06-24 02:00:00');


create table if not exists tasks
(
    id                  bigserial primary key,
    title               varchar(60) not null,
    content             varchar(300),
    user_creator        varchar(60) not null,
    user_executor       varchar(60),
    begin_date          timestamp not null,
    end_date            timestamp not null,
    actual_end_date     timestamp,
    state               varchar(15) not null default 'REGISTERED',
    priority            varchar(15) not null default 'NORMAL',
    kanban_board_id     bigint not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

insert into tasks ( title, content, user_creator, user_executor, begin_date,end_date, state, priority, kanban_board_id)
values  ( 'Create simple program', 'Hello World!', 'user1', 'user2' , '2022-06-24 01:00:00','2022-08-24 01:00:00', 'COMPLETE', 'HIGH', '1' ),
        ( 'Create simple program2', 'Hello World!', 'user1', '', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'CREATED', 'HIGH', '1' ),
        ( 'Create simple program3', 'Hello World!', 'user2', '', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'CREATED', 'NORMAL', '1' ),
        ( 'Create simple program3', 'Hello World!', 'user2', '', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'CREATED', 'NORMAL', '2' ),
        ( 'Create simple program3', 'Hello World!', 'user2', '', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '2' ),
        ( 'Create simple program3', 'Hello World!', 'user2', '', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '1' ),
        ( 'Create simple program3', 'Hello World!', 'user3', '', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '1' ),
        ( 'Create simple program3', 'Hello World!', 'user3', '', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '1' ),
        ( 'KB1 task 1', 'This is task for KB 1 user 1', 'user1', '', '2022-08-20 01:00:00','2022-08-25 01:00:00', 'IN_PROGRESS', 'LOW', '1' ),
        ( 'KB1 task 2', 'This is task for KB 1 user 2', 'user2', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '1' ),
        ( 'KB1 task 3', 'This is task for KB 1 user 3', 'user3', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'CREATED', 'LOW', '1' ),
        ( 'KB1 task 4', 'This is task for KB 1 user 4', 'user4', '', '2022-08-22 01:00:00','2022-08-27 01:00:00', 'CREATED', 'LOW', '1' ),
        ( 'KB2 task 1', 'This is task for KB 2 user 1', 'user1', '', '2022-08-20 01:00:00','2022-08-25 01:00:00', 'IN_PROGRESS', 'LOW', '2' ),
        ( 'KB2 task 2', 'This is task for KB 2 user 2', 'user2', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '2' ),
        ( 'KB2 task 3', 'This is task for KB 2 user 3', 'user3', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'CREATED', 'LOW', '2' ),
        ( 'KB2 task 4', 'This is task for KB 2 user 4', 'user4', '', '2022-08-22 01:00:00','2022-08-27 01:00:00', 'CREATED', 'LOW', '2' ),
        ( 'KB3 task 1', 'This is task for KB 3 user 1', 'user1', '', '2022-08-20 01:00:00','2022-08-25 01:00:00', 'IN_PROGRESS', 'LOW', '3' ),
        ( 'KB3 task 2', 'This is task for KB 3 user 2', 'user2', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '3' ),
        ( 'KB3 task 3', 'This is task for KB 3 user 3', 'user3', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'CREATED', 'LOW', '3' ),
        ( 'KB3 task 4', 'This is task for KB 3 user 4', 'user4', '', '2022-08-22 01:00:00','2022-08-27 01:00:00', 'CREATED', 'LOW', '3' ),
        ( 'KB4 task 1', 'This is task for KB 3 user 1', 'user1', '', '2022-08-20 01:00:00','2022-08-25 01:00:00', 'IN_PROGRESS', 'LOW', '4' ),
        ( 'KB4 task 2', 'This is task for KB 3 user 2', 'user2', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'LOW', '4' ),
        ( 'KB4 task 3', 'This is task for KB 3 user 3', 'user3', '', '2022-08-21 01:00:00','2022-08-24 01:00:00', 'CREATED', 'LOW', '4' ),
        ( 'KB4 task 4', 'This is task for KB 3 user 4', 'user4', '', '2022-08-22 01:00:00','2022-08-27 01:00:00', 'CREATED', 'LOW', '4' );