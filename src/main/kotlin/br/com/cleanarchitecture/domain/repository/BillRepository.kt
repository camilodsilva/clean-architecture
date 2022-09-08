package br.com.cleanarchitecture.domain.repository

import br.com.cleanarchitecture.domain.entity.Bill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BillRepository : JpaRepository<Bill, Long>
