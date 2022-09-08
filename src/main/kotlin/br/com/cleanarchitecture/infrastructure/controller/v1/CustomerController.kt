package br.com.cleanarchitecture.infrastructure.controller.v1

import br.com.cleanarchitecture.domain.component.CustomerComponent
import br.com.cleanarchitecture.domain.pattern.bo.CustomerBO
import br.com.cleanarchitecture.infrastructure.controller.ApiProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${ApiProperties.BASE_PATH}/customers", produces = [ApiProperties.API_HEADER_V1])
class CustomerController(
    private val customerComponent: CustomerComponent,
) {

    @PostMapping
    fun create(@RequestBody customerBO: CustomerBO): ResponseEntity<CustomerBO> =
        ResponseEntity.status(HttpStatus.CREATED).body(customerComponent.create(customerBO))

    @GetMapping("/{customerId}")
    fun findById(@PathVariable customerId: Long): ResponseEntity<CustomerBO> =
        ResponseEntity.ok(customerComponent.retrieve(customerId))
}
