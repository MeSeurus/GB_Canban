create table if not exists events_for_analytics (
    id                  bigserial primary key,
    event_id            bigint not null,
    event_title         varchar(60) not null,
    event_username      varchar(60) not null,
    event_begin_date    timestamp not null,
    event_end_date      timestamp not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
    );

create table if not exists tasks_for_analytics
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
