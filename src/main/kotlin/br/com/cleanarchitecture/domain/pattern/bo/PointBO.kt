package br.com.cleanarchitecture.domain.pattern.bo

import br.com.cleanarchitecture.domain.entity.Point
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PointBO(
    val id: Long? = null,

    @JsonProperty("total_points")
    var totalPoints: Int,

    @JsonProperty("total_amount")
    var totalAmount: BigDecimal,
) {
    fun toPoint() = Point(
        id = id,
        totalPoints = totalPoints,
        totalAmount = totalAmount,
    )

    override fun toString(): String {
        return "PointBO(id=$id, totalPoints=$totalPoints, totalAmount=$totalAmount)"
    }
}
