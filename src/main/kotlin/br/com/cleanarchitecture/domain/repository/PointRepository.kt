package br.com.cleanarchitecture.domain.repository

import br.com.cleanarchitecture.domain.entity.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointRepository: JpaRepository<Point, Long>
