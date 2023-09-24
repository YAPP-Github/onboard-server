package com.yapp.bol.game.rating.strategy

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EloRatingStrategyTest : FunSpec() {
    private val eloRatingStrategy = EloRatingStrategy

    init {
        test("1 on 1, elo 점수가 0 인 두 명이 매치한 경우") {
            val (input1, input2) = RatingTestHelper.creatMatchTestFixture(0, 0)

            eloRatingStrategy.compute(input1) shouldBe 16
            eloRatingStrategy.compute(input2) shouldBe 0
        }

        test("1 on 1, 400점 높은 상대를 이긴 경우") {
            val (input1, input2) = RatingTestHelper.creatMatchTestFixture(0, 400)

            eloRatingStrategy.compute(input1) shouldBe 29 // round(K * (1 - 1/11))
            eloRatingStrategy.compute(input2) shouldBe (371) // round(K * (0 - 10/11))
        }

        test(" 1 on 1, 400점 낮은 상대를 이긴 경우") {
            val (input1, input2, input3) = RatingTestHelper.creatMatchTestFixture(50, 50, 50)

            eloRatingStrategy.compute(input1) shouldBe 50 + 16
            eloRatingStrategy.compute(input2) shouldBe 50
            eloRatingStrategy.compute(input3) shouldBe 50 - 16
        }

        test("multi players, 1등만 elo 점수가 낮은 경우") {
            val (input1, input2, input3) = RatingTestHelper.creatMatchTestFixture(25, 50, 50)

            eloRatingStrategy.compute(input1) shouldBe 42
            eloRatingStrategy.compute(input2) shouldBe 49
            eloRatingStrategy.compute(input3) shouldBe 33
        }
    }
}
