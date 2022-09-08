package br.com.cleanarchitecture.infrastructure.controller.v1

import br.com.cleanarchitecture.domain.component.DiscountComponent
import br.com.cleanarchitecture.domain.pattern.bo.DiscountBO
import br.com.cleanarchitecture.infrastructure.controller.ApiProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${ApiProperties.BASE_PATH}/discounts", produces = [ApiProperties.API_HEADER_V1])
class DiscountController(
    private val discountComponent: DiscountComponent,
) {

    @PostMapping
    fun create(@RequestBody discountBO: DiscountBO): ResponseEntity<DiscountBO> =
        ResponseEntity.ok(discountComponent.create(discountBO))

    @GetMapping("/{discountId}")
    fun findById(@PathVariable discountId: Long): ResponseEntity<DiscountBO> =
        ResponseEntity.ok(discountComponent.retrieve(discountId))
}
