
drop table if exists users;

create table users (
    id int(11) auto_increment,
    username varchar(255) not null,
    password varchar(255) not null
    primary key (id)
) engine = innodb;

insert into users (username, password) values ("admin", "5f4dcc3b5aa765d61d8327deb882cf99");
insert into users (username, password) values ("root", "5f4dcc3b5aa765d61d8327deb882cf99");