package br.com.cleanarchitecture.domain.pattern.bo

import br.com.cleanarchitecture.domain.entity.Customer
import br.com.cleanarchitecture.domain.enums.CustomerProfile
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CustomerBO(
    var id: Long? = null,
    var name: String? = null,

    @JsonProperty("last_name")
    var lastName: String? = null,
    var email: String,
    var pass: String,
    var profile: CustomerProfile,
) {
    fun toCustomer() = Customer(
        id = id,
        name = name,
        lastName = lastName,
        email = email,
        pass = pass,
        profile = profile,
    )

    override fun toString(): String {
        return "CustomerBO(id=$id, email='$email', profile=$profile)"
    }
}
