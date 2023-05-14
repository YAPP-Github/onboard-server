create table auth_social
(
    social_type varchar(10) NOT NULL,
    social_id   varchar(50) NOT NULL,
    user_id     bigint      NOT NULL,
    created_at  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY auth_social (social_id, social_type)
);
