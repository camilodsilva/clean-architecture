package br.com.cleanarchitecture.application.infrastructure.controller.v1

import br.com.cleanarchitecture.application.domain.pattern.bo.mock.CustomerMockBO
import br.com.cleanarchitecture.domain.component.CustomerComponent
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
class CustomerControllerTest(
    @Value("\${spring.security.user.name}")
    private var user: String,

    @Value("\${spring.security.user.password}")
    private var pass: String,
) : BaseControllerTest(user, pass) {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var customerComponent: CustomerComponent

    @Test
    fun shouldCreateAnCustomer() {
        mockMvc.perform(
            performPost("customers").content(
                objectMapper.writeValueAsString(CustomerMockBO.getCustomerBO())
            )
        )
            .andDo(print())
            .andExpect(status().isCreated)
    }

    @Test
    fun shouldRetrieveAnCustomer() {
        val createdCustomer = customerComponent.create(CustomerMockBO.getCustomerBO())

        mockMvc.perform(performGet("customers/${createdCustomer.id}"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(createdCustomer.name))
    }
}
