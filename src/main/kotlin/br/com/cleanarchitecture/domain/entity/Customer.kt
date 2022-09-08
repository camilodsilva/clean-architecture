package br.com.cleanarchitecture.domain.entity

import br.com.cleanarchitecture.domain.enums.CustomerProfile
import br.com.cleanarchitecture.domain.pattern.bo.CustomerBO
import java.io.Serializable
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "customer")
@SequenceGenerator(name = "sq_customer", sequenceName = "sq_customer", allocationSize = 1)
data class Customer(
    @Id
    @Column(name = "customer_idt")
    @GeneratedValue(generator = "sq_customer", strategy = GenerationType.SEQUENCE)
    val id: Long?,

    @Column(name = "name")
    var name: String?,

    @Column(name = "last_name")
    var lastName: String?,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "pass", nullable = false)
    var pass: String,

    @Column(name = "profile", nullable = false)
    @Enumerated(EnumType.STRING)
    var profile: CustomerProfile,

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant? = null,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant? = null

) : Serializable {
    @PrePersist
    fun onPersist() {
        if (createdAt == null)
            createdAt = Instant.now()

        if (updatedAt == null)
            updatedAt = Instant.now()
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = Instant.now()
    }

    fun toCustomerBO() = CustomerBO(
        id = id,
        name = name,
        lastName = lastName,
        email = email,
        pass = pass,
        profile = profile,
    )

    override fun toString(): String {
        return "Customer(id=$id, email='$email', profile=$profile)"
    }
}
