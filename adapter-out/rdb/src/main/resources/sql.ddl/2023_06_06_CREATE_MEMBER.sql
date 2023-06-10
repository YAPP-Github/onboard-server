CREATE TABLE member (
    member_id BIGINT NOT NULL AUTO_INCREMENT,
    users_id BIGINT,
    role VARCHAR(255) NOT NULL,
    nickname VARCHAR(6) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    group_id BIGINT NOT NULL,
    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_member (member_id),
    KEY IDX_groupId (group_id)
);
