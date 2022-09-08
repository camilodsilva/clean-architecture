package br.com.cleanarchitecture.domain.pattern.bo

import br.com.cleanarchitecture.domain.entity.Bill
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BillBO(
    val id: Long? = null,
    val customerBO: CustomerBO,
    val discountBO: DiscountBO? = null,
    var amount: BigDecimal,

    @JsonProperty("amount_with_discount")
    var amountWithDiscount: BigDecimal,

    @JsonProperty("generated_points")
    var generatedPoints: Int,
    var location: String,
) {
    init {
        amountWithDiscount = calculateAmountWithDiscount()
        generatedPoints = generatePoints()
    }

    fun generatePoints(): Int {
        return amount.toInt()
    }

    fun calculateAmountWithDiscount(): BigDecimal {
        return discountBO?.let { amount.minus(it.amount) } ?: amount
    }

    fun toBill() = Bill(
        id = id,
        customer = customerBO.toCustomer(),
        discount = discountBO?.toDiscount(),
        amount = amount,
        amountWithDiscount = amountWithDiscount,
        generatedPoints = generatedPoints,
        location = location,
    )
}
