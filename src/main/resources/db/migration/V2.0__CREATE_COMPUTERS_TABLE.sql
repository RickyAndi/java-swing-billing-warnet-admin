
drop table if exists computers;

create table computers (
    `id` int auto_increment primary key,
    `name` varchar(255) not null,
    `current_username` varchar(255) null,
    `last_start` datetime null,
    `status` tinyint not null
) ENGINE = INNODB;

insert into computers (name, status) values ('PC-1', 0);
insert into computers (name, status) values ('PC-2', 0);
insert into computers (name, status) values ('PC-3', 0);
insert into computers (name, status) values ('PC-4', 0);
insert into computers (name, status) values ('PC-5', 0);
insert into computers (name, status) values ('PC-6', 0);
insert into computers (name, status) values ('PC-7', 0);