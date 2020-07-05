drop table if exists `settings`;
create table `settings` (
    `id` int(11) auto_increment primary key,
    `name` varchar(255) not null,
    `type` varchar(255) not null,
    `string_value` varchar(255) null,
    `integer_value` int null,
    `decimal_value` decimal(13, 4) null
) ENGINE = INNODB;

insert into settings (name, type, string_value) values ('NAMA_WARNET', 'string', 'HOMEINET-ID');
insert into settings (name, type, string_value) values ('ALAMAT_WARNET', 'string', 'Jl. Gajah mada no 11, Kota Batu, Jawa timur');
insert into settings (name, type, integer_value) values ('BIAYA_PER_JAM', 'integer', 5000);