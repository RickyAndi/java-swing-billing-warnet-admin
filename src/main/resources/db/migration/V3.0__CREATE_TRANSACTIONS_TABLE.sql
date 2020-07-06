
drop table if exists `transactions`;

create table `transactions` (
     `id` int auto_increment primary key,
     `computer_id` int(11) null,
     `username` varchar(255) not null,
     `start_on` datetime not null,
     `end_on` datetime null,
     `amount_to_be_paid` decimal(13, 3),
     `amount_paid_by_client` decimal(13, 3),
     `amount_change` decimal(13, 3),
     `status` tinyint not null
);

alter table `transactions` add foreign key (computer_id)
references computers(id)
on update cascade
on delete set null;

insert into `transactions` (computer_id, username, start_on, end_on, amount_to_be_paid, amount_paid_by_client, amount_change, status)
values (1, 'budimen', '2020-07-01 12:00', '2020-07-01 15:00', 15000, 15000, 0, 2),
(1, 'niken', '2020-07-01 16:00', '2020-07-01 21:00', 30000, 30000, 0, 2),
(2, 'rudi', '2020-07-01 09:00', '2020-07-01 13:15', 15000, 15000, 0, 2),
(2, 'margaretha', '2020-07-01 12:00', '2020-07-01 15:00', 15000, null, null, 1),
(2, 'kucing', '2020-07-01 12:00', '2020-07-01 15:00', 15000, null, null, 1),
(2, 'superman', '2020-07-01 12:00', '2020-07-01 15:00', 15000, null, null, 1),
(2, 'sapiderman', '2020-07-01 12:00', '2020-07-01 15:00', 15000, null, null, 1)
;