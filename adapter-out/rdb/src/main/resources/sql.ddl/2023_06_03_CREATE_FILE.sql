create table file
(
    file_id      bigint      NOT NULL AUTO_INCREMENT,
    name         varchar(50) NOT NULL,
    users_id     bigint      NOT NULL,
    access_level varchar(10) NOT NULL DEFAULT 'PUBLIC',
    created_at   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_users (file_id)
);
