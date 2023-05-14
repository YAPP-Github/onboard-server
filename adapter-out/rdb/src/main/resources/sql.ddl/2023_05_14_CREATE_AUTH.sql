create table auth_social
(
    social_type varchar(10) NOT NULL,
    social_id   varchar(50) NOT NULL,
    user_id     bigint      NOT NULL,
    created_at  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY auth_social (social_id, social_type)
);

CREATE TABLE auth_access_token
(
    access_token_id bigint   NOT NULL AUTO_INCREMENT,
    access_token    binary(30) NOT NULL,
    user_id         bigint   NOT NULL,
    expire_at       DATETIME NOT NULL,
    create_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_authAccessToken (access_token_id),
    UNIQUE KEY idx_accesstoken (access_token)
);

CREATE TABLE auth_refresh_token
(
    refresh_token_id bigint   NOT NULL AUTO_INCREMENT,
    refresh_token    binary(45) NOT NULL,
    user_id          bigint   NOT NULL,
    expire_at        DATETIME NOT NULL,
    create_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_authRefreshToken (refresh_token_id),
    UNIQUE KEY idx_refreshtoken (refresh_token)
);
