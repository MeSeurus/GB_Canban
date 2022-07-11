create table if not exists days
(
    id              bigserial primary key,
    cur_date        date not null unique,
    weekday         int not null
    );

create table if not exists users
(
    id              bigserial primary key,
    nickname        varchar(60) not null

    );

create table if not exists events
(
    id              bigserial primary key,
    title           varchar(60) not null,
    content         varchar(300) not null,
    user_id         bigint references users (id) not null,
    day_id          bigint references days (id) not null,
    event_start     date,
    event_end       date

);


insert into days (id, cur_date, weekday)
values (1, '2022-06-24', 5),
       (2, '2022-06-25', 6);

insert into users (id, nickname)
values (1, 'user1'),
       (2, 'user2');

insert into events (id, title, content, user_id, day_id, event_start, event_end)
values (1, 'Create simple program', 'Hello World!', 1, 1, '2022-06-24 01:00:00', '2022-06-24 07:00:00'),
       (2, 'Create hard program', 'Hello World2!!', 1, 1, '2022-06-24 01:00:00', '2022-06-24 07:00:00'),
       (3, 'Create SUPER hard program', 'Hello World3!!', 2, 1, '2022-06-24 01:00:00', '2022-06-24 07:00:00'),
       (4, 'Create SUPER hard program', 'Hello World3!!', 2, 2, '2022-06-24 01:00:00', '2022-06-24 07:00:00');







