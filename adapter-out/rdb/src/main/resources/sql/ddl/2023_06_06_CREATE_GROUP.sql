create table group_table
(
    group_id          bigint      not null auto_increment,
    name              varchar(14) not null,
    description       varchar(72) not null,
    organization      varchar(15),
    profile_image_url varchar(255) null,
    access_code       varchar(8)  not null,
    deleted           tinyint(1) default 0 not null,
    created_at        datetime    not null default current_timestamp,
    updated_at        datetime    not null default current_timestamp on update current_timestamp,
    primary key pk_groups (group_id)
);
