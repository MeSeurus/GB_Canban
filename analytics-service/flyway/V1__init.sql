create table if not exists events_analytics (
    id                  bigserial primary key,
    event_id            bigint not null,
    event_title         varchar(60) not null,
    event_username      varchar(60) not null,
    event_begin_date    date not null,
    event_end_date      date not null,
    added_usernames     varchar(300),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table if not exists tasks_analytics
(
    id                      bigserial primary key,
    task_id                 bigint not null,
    task_title              varchar(60) not null,
    task_username           varchar(60) not null,
    task_begin_date         date not null,
    task_end_date           date not null,
    task_actual_end_date    date,
    task_state              varchar(30) not null default 'REGISTERED',
    task_priority           varchar(30) not null default 'NORMAL',
    task_kanban_name        varchar(50) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into events_analytics (event_id, event_title, event_username, event_begin_date, event_end_date, added_usernames)
values (1, 'Create simple program', 'user3', '2022-06-24 01:00:00', '2022-06-24 03:00:00'),
       (2, 'Create hard program', 'user3', '2022-06-24 01:00:00', '2022-06-24 07:00:00', 'user1');

insert into tasks_analytics (task_id, task_title, task_username, task_begin_date, task_end_date,task_actual_end_date, task_state, task_priority, task_kanban_name)
values  (1, 'Create simple program', 'user3', '2022-06-24 01:00:00','2022-06-30 01:00:00', '2022-08-26 01:00:00', 'COMPLETE', 'HIGH', 'kanban1' ),
        (2, 'Create simple program2', 'user1', '2022-06-24 01:00:00', '2022-06-30 01:00:00', '', 'CREATED', 'HIGH', 'kanban1' );

