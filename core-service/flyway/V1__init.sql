create table if not exists events
(
    id              bigserial primary key,
    title           varchar(60) not null,
    content         varchar(300) not null,
    username        varchar(60),
    event_date      date
);


insert into events (id, title, content, username, event_date)
values (1, 'Create simple program', 'Hello World!', 'user1', '2022-06-24 01:00:00'),
       (2, 'Create hard program', 'Hello World2!!', 'user2', '2022-06-24 01:00:00'),
       (3, 'Create SUPER hard program', 'Hello World3!!', 'user3', '2022-06-24 01:00:00'),
       (4, 'Create SUPER hard program', 'Hello World3!!', 'user4', '2022-06-24 01:00:00');

create table if not exists tasks
(
    id              bigserial primary key,
    title           varchar(60) not null,
    content         varchar(300) not null,
    username        varchar(60),
    event_date      date,
    due_date        date,
    state           varchar(15),
    priority        varchar(15),
    kanban_name     varchar(15)
);

insert into tasks (id, title, content, username, event_date,due_date,state,priority,kanban_name)
values  (1, 'Create simple program', 'Hello World!', 'user1', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'IN_PROGRESS', 'HIGH', 'kanban1' ),
        (2, 'Create simple program2', 'Hello World!', 'user1', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'COMPLETED', 'NORMAL', 'kanban1' ),
        (3, 'Create simple program3', 'Hello World!', 'user2', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'CREATED', 'LOW', 'kanban1' ),
        (4, 'Create simple program3', 'Hello World!', 'user2', '2022-06-24 01:00:00','2022-08-24 01:00:00', 'COMPLETED', 'LOW', 'kanban1' );




