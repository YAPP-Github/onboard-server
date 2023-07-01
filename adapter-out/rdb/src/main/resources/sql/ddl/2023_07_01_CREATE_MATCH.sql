CREATE TABLE match_table
(
    match_id        bigint       NOT NULL AUTO_INCREMENT COMMENT 'PK',
    log             varchar(255) NOT NULL COMMENT '매치 기록',
    match_image_url varchar(255) NOT NULL COMMENT '매치 이미지 url',
    matched_date    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '매치 날짜',
    member_count    int          NOT NULL COMMENT '멤버 수',
    season_id       bigint       NOT NULL COMMENT '시즌 ID',
    game_id         bigint       NOT NULL COMMENT '게임 ID',
    group_id        bigint       NOT NULL COMMENT '그룹 ID',
    created_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최근 수정 일시',
    PRIMARY KEY PK_match (match_id)
) COMMENT '매치 테이블';
