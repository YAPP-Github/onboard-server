package yapp.rating

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FailTest : FunSpec() {
    init {
        test("Fail Test"){
            true shouldBe false
        }
    }
}
