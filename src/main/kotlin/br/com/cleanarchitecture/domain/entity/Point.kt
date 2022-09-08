package br.com.cleanarchitecture.domain.entity

import br.com.cleanarchitecture.domain.pattern.bo.PointBO
import java.io.Serializable
import java.math.BigDecimal
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "point")
@SequenceGenerator(name = "sq_point", sequenceName = "sq_point", allocationSize = 1)
data class Point(
    @Id
    @Column(name = "point_idt")
    @GeneratedValue(generator = "sq_point", strategy = GenerationType.SEQUENCE)
    val id: Long?,

    @Column(name = "total_points", nullable = false)
    var totalPoints: Int,

    @Column(name = "total_amount", nullable = true)
    var totalAmount: BigDecimal,

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

    fun toPointBO() = PointBO(
        id = id,
        totalPoints = totalPoints,
        totalAmount = totalAmount,
    )

    override fun toString(): String {
        return "Point(id=$id, points=$totalPoints, amount=$totalAmount, createdAt=$createdAt, updatedAt=$updatedAt)"
    }

}
