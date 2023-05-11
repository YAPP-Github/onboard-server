package yapp.rating

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TestControllerTest : ControllerTest() {
    override val controller = TestController()

    init {
        test("Get Test") {
            mockMvc.perform(get("/v1/test"))
                .andExpect(status().isOk)
                .andDo(
                    document("test")
                )
        }
    }
}
