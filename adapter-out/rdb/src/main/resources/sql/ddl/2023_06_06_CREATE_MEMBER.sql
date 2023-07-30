create table member
(
    member_id  bigint   not null auto_increment,
    users_id   bigint null,
    role       varchar(255) null,
    nickname   varchar(6) null,
    level      int      not null default 0,
    deleted    tinyint(1) default 0 not null,
    group_id   bigint null,
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp on update current_timestamp,
    primary key pk_member (member_id),
    key        idx_groupid (group_id),
    unique unq_userid_groupid (users_id, group_id)
);
