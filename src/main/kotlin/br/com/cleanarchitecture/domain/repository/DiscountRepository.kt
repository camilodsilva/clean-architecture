package br.com.cleanarchitecture.domain.repository

import br.com.cleanarchitecture.domain.entity.Discount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountRepository : JpaRepository<Discount, Long>
