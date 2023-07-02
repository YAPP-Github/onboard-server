CREATE TABLE season
(
    season_id        bigint       NOT NULL AUTO_INCREMENT COMMENT 'PK',
    group_id        bigint       NOT NULL COMMENT '그룹 ID',
    created_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최근 수정 일시',
    PRIMARY KEY PK_match (season_id)
) COMMENT '시즌 테이블';
