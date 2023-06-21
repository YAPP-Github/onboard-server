create table file
(
    file_id    bigint      NOT NULL AUTO_INCREMENT,
    name       varchar(50) NOT NULL,
    users_id   bigint      NOT NULL,
    purpose    varchar(20) NOT NULL,
    created_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_file (file_id)
);
