package br.com.cleanarchitecture.domain.repository

import br.com.cleanarchitecture.domain.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>
