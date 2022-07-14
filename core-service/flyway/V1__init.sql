create table if not exists events
(
    id              bigserial primary key,
    title           varchar(60) not null,
    content         varchar(300) not null,
    userName        varchar(60),
    event_date      date
);


insert into events (id, title, content, userName, event_date)
values (1, 'Create simple program', 'Hello World!', 'user1', '2022-06-24 01:00:00'),
       (2, 'Create hard program', 'Hello World2!!', 'user2', '2022-06-24 01:00:00'),
       (3, 'Create SUPER hard program', 'Hello World3!!', 'user3', '2022-06-24 01:00:00'),
       (4, 'Create SUPER hard program', 'Hello World3!!', 'user4', '2022-06-24 01:00:00');

create table if not exists tasks
(
    id              bigserial primary key,
    title           varchar(60) not null,
    content         varchar(300) not null,
    userName        varchar(60),
    event_date      date,
    due_date        date,
    state           varchar(15),
    priority        varchar(15),
    kanbanName      varchar(15)
);





