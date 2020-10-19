create table Border
(
    id       int not null primary key,
    name varchar(255) null,
    cars int not null,
    waiting_time varchar(255) null,
    date datetime null
);

alter table Border
    add column user_id int not null,
    add column username varchar(255) null;

alter table Border
    add column hu bit not null;