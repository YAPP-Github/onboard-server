CREATE TABLE agreed_terms
(
    agreed_terms_id bigint      NOT NULL AUTO_INCREMENT COMMENT 'PK',
    user_id         bigint      NOT NULL COMMENT '유저 ID',
    category        varchar(20) NOT NULL COMMENT '동의한 약관 종류',
    version         int         NOT NULL COMMENT '동의한 약관 버전',
    created_at      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최근 수정 일시',
    PRIMARY KEY PK_match (agreed_terms_id)
) COMMENT '동의한 약관 정보';
