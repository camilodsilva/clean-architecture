package br.com.cleanarchitecture.application.infrastructure.controller.v1

import br.com.cleanarchitecture.infrastructure.controller.ApiProperties
import org.springframework.http.HttpMethod
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request

open class BaseControllerTest(
    private val user: String,
    private val pass: String,
) {

    fun performPost(uri: String): MockHttpServletRequestBuilder =
        performRequest(HttpMethod.POST, uri)

    private fun performRequest(httpMethod: HttpMethod, uri: String): MockHttpServletRequestBuilder =
        request(httpMethod, "${ApiProperties.BASE_PATH}/$uri")
            .contentType("application/json")
            .accept(ApiProperties.API_HEADER_V1)
            .with(user(user).password(pass))

    fun performGet(uri: String): MockHttpServletRequestBuilder =
        performRequest(HttpMethod.GET, uri)
}
