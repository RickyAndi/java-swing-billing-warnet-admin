
drop table if exists computers;

create table computers (
    `id` int auto_increment primary key,
    `name` varchar(255) not null,
    `current_username` varchar(255) null,
    `current_price_per_hour` decimal(13, 4) null,
    `last_start` datetime null,
    `status` tinyint not null
) ENGINE = INNODB;

insert into computers (name, current_username, current_price_per_hour, last_start, status)
values ('PC-1', 'niken', 5000, '2020-07-09 12:00', 1);
insert into computers (name, current_username, current_price_per_hour, last_start, status)
values ('PC-2', 'margaretha',  5000, '2020-07-09 13:00', 1);
insert into computers (name, status) values ('PC-3', 0);
insert into computers (name, status) values ('PC-4', 0);
insert into computers (name, status) values ('PC-5', 0);
insert into computers (name, status) values ('PC-6', 0);
insert into computers (name, status) values ('PC-7', 0);