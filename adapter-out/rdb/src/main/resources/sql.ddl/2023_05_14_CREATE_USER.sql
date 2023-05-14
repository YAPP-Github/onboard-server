create table user
(
    user_id     bigint      NOT NULL AUTO_INCREMENT,
    name        varchar(20) NOT NULL,
    created_at  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted     bit         NOT NULL,
    PRIMARY KEY PK_user (user_id)
);
