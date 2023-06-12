CREATE TABLE group_table (
    group_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(14) NOT NULL,
    description VARCHAR(72) NOT NULL,
    organization VARCHAR(15) NOT NULL,
    profile_image_url VARCHAR(255),
    access_code CHAR(6) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_groups (group_id)
);

