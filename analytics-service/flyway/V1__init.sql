create table if not exists events_analytics (
    id                  bigserial primary key,
    event_id            bigint not null,
    event_title         varchar(60) not null,
    event_username      varchar(60) not null,
    event_begin_date    timestamp not null,
    event_end_date      timestamp not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table if not exists tasks_analytics
(
    id                      bigserial primary key,
    task_id                 bigint not null,
    task_title              varchar(60) not null,
    task_user_creator       varchar(60) not null,
    task_user_executor      varchar(60),
    task_begin_date         timestamp not null,
    task_end_date           timestamp not null,
    task_actual_end_date    timestamp,
    task_state              varchar(30) not null default 'REGISTERED',
    task_priority           varchar(30) not null default 'NORMAL',
    task_kanban_board_id    bigint not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into events_analytics (event_id, event_title, event_username, event_begin_date, event_end_date)
values (1, 'Create simple program', 'user3', '2022-06-24 01:00:00', '2022-06-28 03:00:00'),
       (2, 'Create hard program', 'user3', '2022-07-24 01:00:00', '2022-07-24 03:00:00'),
       (3, 'Create hard program', 'user3', '2022-07-28 01:00:00', '2022-08-03 03:00:00'),
       (4, 'Create hard program', 'user3', '2022-08-01 05:00:00', '2022-08-03 07:00:00'),
       (5, 'Create hard program', 'user3', '2022-07-10 01:00:00', '2022-08-02 07:00:00');

insert into tasks_analytics (task_id, task_title, task_user_creator, task_user_executor, task_begin_date, task_end_date,task_actual_end_date, task_state, task_priority, task_kanban_board_id)
values  (1, 'Create simple program', 'user3', '', '2022-06-24 01:00:00','2022-06-30 01:00:00', '2022-08-26 01:00:00' , 'COMPLETE', 'HIGH', '1'),
        (2, 'Create simple program2', 'user1', 'user3', '2022-06-24 01:00:00', '2022-06-30 01:00:00', '2022-06-29 01:00:00', 'CREATED', 'HIGH', '1'),
        (2, 'Create simple program2', 'user1', 'user3', '2022-06-24 01:00:00', '2022-06-30 01:00:00', '2022-06-25 01:00:00', 'CREATED', 'HIGH', '1'),
        (2, 'Create simple program2', 'user1', 'user1', '2022-06-24 01:00:00', '2022-06-30 01:00:00', '2022-06-29 01:00:00', 'CREATED', 'HIGH', '1'),
        (2, 'Create simple program2', 'user1', 'user1', '2022-06-24 01:00:00', '2022-06-30 01:00:00', '2022-06-29 01:00:00', 'CREATED', 'HIGH', '1');

