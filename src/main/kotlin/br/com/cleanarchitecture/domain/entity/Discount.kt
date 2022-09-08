package br.com.cleanarchitecture.domain.entity

import br.com.cleanarchitecture.domain.enums.DiscountStatus
import br.com.cleanarchitecture.domain.pattern.bo.DiscountBO
import java.io.Serializable
import java.math.BigDecimal
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "discount")
@SequenceGenerator(name = "sq_discount", sequenceName = "sq_discount", allocationSize = 1)
data class Discount(
    @Id
    @Column(name = "discount_idt")
    @GeneratedValue(generator = "sq_discount", strategy = GenerationType.SEQUENCE)
    val id: Long?,

    @Column(name = "amount", nullable = false)
    var amount: BigDecimal,

    @Column(name = "points", nullable = false)
    var points: Int,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: DiscountStatus,

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

    fun toDiscountBO() = DiscountBO(
        id = id,
        amount = amount,
        points = points,
        status = status,
    )

    override fun toString(): String {
        return "Discount(id=$id, amount=$amount, points=$points, status=$status, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
