CREATE TABLE match_member
(
    match_member_id bigint   NOT NULL AUTO_INCREMENT COMMENT 'PK',
    match_id        bigint   COMMENT '매치 id',
    member_id       bigint   NOT NULL COMMENT '멤버 id',
    score           int      NOT NULL COMMENT '매치 점수',
    previous_score  int      NOT NULL COMMENT '직전 점수',
    ranking         int      NOT NULL COMMENT '매치 순위',
    created_at      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    updated_at      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최근 수정 일시',
    PRIMARY KEY PK_match_member (match_member_id),
    KEY IDX_matchId (match_id)
) COMMENT '매치 멤버 테이블';
