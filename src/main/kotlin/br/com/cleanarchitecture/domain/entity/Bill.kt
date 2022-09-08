package br.com.cleanarchitecture.domain.entity

import br.com.cleanarchitecture.domain.pattern.bo.BillBO
import java.io.Serializable
import java.math.BigDecimal
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "bill")
@SequenceGenerator(name = "sq_bill", sequenceName = "sq_bill", allocationSize = 1)
data class Bill(
    @Id
    @Column(name = "bill_idt")
    @GeneratedValue(generator = "sq_bill", strategy = GenerationType.SEQUENCE)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "customer_fky", nullable = true)
    val customer: Customer,

    @OneToOne
    @JoinColumn(name = "discount_fky", nullable = true)
    val discount: Discount?,

    @Column(name = "amount", nullable = false)
    var amount: BigDecimal,

    @Column(name = "amount_with_discount", nullable = false)
    var amountWithDiscount: BigDecimal,

    @Column(name = "generated_points", nullable = false)
    var generatedPoints: Int,

    @Column(name = "location", nullable = false)
    var location: String,

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

    fun toBillBO() = BillBO(
        id = id,
        customerBO = customer,
        discountBO = discount,
        amount = amount,
        amountWithDiscount = amountWithDiscount,
        generatedPoints = generatedPoints,
        location = location,
    )
}
