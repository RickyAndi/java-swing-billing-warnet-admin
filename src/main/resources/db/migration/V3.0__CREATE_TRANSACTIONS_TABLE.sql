
drop table if exists `transactions`;

create table `transactions` (
     `id` int auto_increment primary key,
     `computer_id` int(11) null,
     `username` varchar(255) not null,
     `start_on` timestamp not null,
     `ends_on` timestamp null,
     `amount_to_be_paid` decimal(13, 3),
     `amount_paid_by_client` decimal(13, 3),
     `amount_change` decimal(13, 3),
     `status` tinyint not null
);

alter table `transactions` add foreign key (computer_id)
references computers(id)
on update cascade
on delete set null;
