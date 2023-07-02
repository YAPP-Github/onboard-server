CREATE TABLE game_member
(
    game_member_id     bigint   NOT NULL AUTO_INCREMENT COMMENT 'PK',
    game_id            bigint   NOT NULL COMMENT '게임 ID',
    member_id          bigint   NOT NULL COMMENT '멤버 ID',
    season_id          bigint   NOT NULL COMMENT '시즌 ID',
    final_score        int      NOT NULL COMMENT '최종 점수',
    match_count        int      NOT NULL COMMENT '매치 수',
    winning_percentage DOUBLE   NOT NULL COMMENT '승률',
    created_at         datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at         datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최근 수정 일시',
    PRIMARY KEY PK_game_member (game_member_id)
) COMMENT '게임 멤버 테이블';
