create table game
(
    game_id    bigint      NOT NULL AUTO_INCREMENT COMMENT 'PK',
    name       varchar(50) NOT NULL COMMENT '게임 이름',
    min_member int         NOT NULL COMMENT '게임 최소 인원수',
    max_member int         NOT NULL COMMENT '게임 최대 인원수',
    rank_type  varchar(20) NOT NULL COMMENT '게임 등수 정책',
    img_id     bigint      NOT NULL COMMENT '이미지 File id',
    created_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최근 수정 일시',
    PRIMARY KEY PK_game (game_id)
) COMMENT 'Game 메타 데이터';
