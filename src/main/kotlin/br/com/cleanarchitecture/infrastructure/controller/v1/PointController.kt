package br.com.cleanarchitecture.infrastructure.controller.v1

import br.com.cleanarchitecture.domain.component.PointComponent
import br.com.cleanarchitecture.domain.pattern.bo.PointBO
import br.com.cleanarchitecture.infrastructure.controller.ApiProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${ApiProperties.BASE_PATH}/points", produces = [ApiProperties.API_HEADER_V1])
class PointController(
    private val pointComponent: PointComponent,
) {

    @PostMapping
    fun create(@RequestBody pointBO: PointBO): ResponseEntity<PointBO> =
        ResponseEntity.ok(pointComponent.create(pointBO))

    @GetMapping("/{discountId}")
    fun findById(@PathVariable pointId: Long): ResponseEntity<PointBO> =
        ResponseEntity.ok(pointComponent.retrieve(pointId))
}
