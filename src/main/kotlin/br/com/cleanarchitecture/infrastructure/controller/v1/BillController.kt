package br.com.cleanarchitecture.infrastructure.controller.v1

import br.com.cleanarchitecture.domain.component.BillComponent
import br.com.cleanarchitecture.domain.pattern.bo.BillBO
import br.com.cleanarchitecture.infrastructure.controller.ApiProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${ApiProperties.BASE_PATH}/bills", produces = [ApiProperties.API_HEADER_V1])
class BillController(
    private val billComponent: BillComponent,
) {

    @PostMapping
    fun create(@RequestBody billBO: BillBO): ResponseEntity<BillBO> =
        ResponseEntity.ok(billComponent.create(billBO))

    @GetMapping("/{discountId}")
    fun findById(@PathVariable billId: Long): ResponseEntity<BillBO> =
        ResponseEntity.ok(billComponent.retrieve(billId))
}
