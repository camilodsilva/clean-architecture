package br.com.cleanarchitecture.domain.pattern.bo

import br.com.cleanarchitecture.domain.entity.Discount
import br.com.cleanarchitecture.domain.enums.DiscountStatus
import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DiscountBO(
    val id: Long? = null,
    var amount: BigDecimal,
    var points: Int,
    var status: DiscountStatus,
) {
    fun toDiscount() = Discount(
        id = id,
        amount = amount,
        points = points,
        status = status,
    )

    override fun toString(): String {
        return "DiscountBO(id=$id, amount=$amount, points=$points, status=$status)"
    }
}
