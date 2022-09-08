package br.com.cleanarchitecture.domain.pattern.dto.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BillReceiverDTO(
    @JsonProperty("customer_id")
    val customerId: Long,

    @JsonProperty("discount_id")
    val discountId: Long? = null,

    val amount: BigDecimal,
    val location: String,
)
