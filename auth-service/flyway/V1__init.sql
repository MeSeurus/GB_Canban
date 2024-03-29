create table users
(
    id         bigserial primary key,
    first_name varchar(80) not null,
    last_name  varchar(80) not null,
    username   varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    status     varchar(30) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

CREATE TABLE users_activation_codes
(
    username varchar(30) not null references users (username),
    secret_code varchar(255) not null,
    codetype varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (username)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

-- пароль user и admin - 100 --
insert into users (username, first_name, last_name, password, email, status)
values ('user', 'Anna', 'Davis', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'anna_davis@gmail.com','ACTIVE'),
       ('admin', 'Jenni', 'Hopkins', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'jenni_hopkins@gmail.com','DISABLED');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);