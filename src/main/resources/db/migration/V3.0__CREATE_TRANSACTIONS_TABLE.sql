
create table `transactions` (
     `id` int auto_increment primary key,
     `computer_id` int unsigned null,
     `username` varchar(255) not null,
     `start_on` datetime not null,
     `ends_on` datetime null,
     `status` tinyint not null
);

alter table `transactions` add constraint fk_computers_transaction
foreign key (computer_id) references computers('id')
on update cascade
on delete set null;
