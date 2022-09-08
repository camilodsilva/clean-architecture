package br.com.cleanarchitecture.application.domain.pattern.bo.mock

import br.com.cleanarchitecture.domain.enums.CustomerProfile
import br.com.cleanarchitecture.domain.pattern.bo.CustomerBO

object CustomerMockBO {
    fun getCustomerBO(
        name: String = "Mocked Name",
        lastName: String = "Mocked Last Name",
        email: String = "Mocked Email",
        pass: String = "Mocked Pass",
        profile: CustomerProfile = CustomerProfile.CUSTOMER
    ) = CustomerBO(
        id = null,
        name = name,
        lastName = lastName,
        email = email,
        pass = pass,
        profile = profile,
    )
}
