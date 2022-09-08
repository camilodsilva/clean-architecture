package br.com.cleanarchitecture.domain.component.impl

import br.com.cleanarchitecture.domain.component.PointComponent
import br.com.cleanarchitecture.domain.entity.Point
import br.com.cleanarchitecture.domain.exception.EntityNotExistsException
import br.com.cleanarchitecture.domain.pattern.bo.PointBO
import br.com.cleanarchitecture.domain.repository.PointRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(propagation = Propagation.REQUIRED)
class PointComponentImpl(
    private val pointRepository: PointRepository
) : PointComponent {

    override fun create(businessObject: PointBO): PointBO {
        LOGGER.info("I=Iniciando persistencia dos pontos gerados, pointBO={}", businessObject)
        val created = save(businessObject.toPoint()).toPointBO()

        LOGGER.info("I=Retornando pontos, pointBO={}", created)
        return created
    }

    private fun save(point: Point): Point {
        LOGGER.info("I=Persistindo pontos gerados, point={}", point)
        val saved = pointRepository.save(point)

        LOGGER.info("I=Pontos persistidos com sucesso, point={}", point)
        return saved
    }

    @Transactional(readOnly = true)
    override fun retrieve(identity: Long): PointBO? {
        LOGGER.info("I=Iniciando busca dos pontos gerados, pointId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Pontos encontrados, pointBO={}", it)
            it.toPointBO()
        } ?: run {
            LOGGER.warn("I=Pontos nao encontrados, pointId={}", identity)
            null
        }
    }

    private fun findById(pointId: Long): Point? {
        val found = pointRepository.findById(pointId)

        if (found.isEmpty) {
            return null
        }

        return found.get()
    }

    @Throws(EntityNotExistsException::class)
    override fun update(businessObject: PointBO): PointBO {
        LOGGER.info("I=Iniciando atualizacao dos pontos gerados, pointBO={}", businessObject)
        val pointId = businessObject.id ?: throw EntityNotExistsException()

        LOGGER.info("I=Iniciando busca dos pontos gerados, pointId={}", pointId)
        val found = findById(pointId)

        return found?.let {
            LOGGER.info("I=Pontos encontrados. Atualizando informacoes, pointBO={}", it)

            it.totalPoints = businessObject.totalPoints
            it.totalAmount = businessObject.totalAmount

            LOGGER.info("I=Iniciando persistencia dos pontos gerados, pointBO={}", it)
            val updated = save(it).toPointBO()

            LOGGER.info("I=Retornando pontos, pointBO={}", updated)
            return@let updated
        } ?: run {
            LOGGER.warn("I=Pontos nao encontrados, pointId={}", pointId)
            throw EntityNotExistsException()
        }
    }

    override fun delete(identity: Long): Boolean {
        LOGGER.info("I=Iniciando busca dos pontos gerados, pointId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Iniciando exclusao dos pontos gerados, pointId={}", identity)
            pointRepository.delete(it)

            LOGGER.info("I=Desconto excluido com sucesso, pointId={}", identity)
            return true
        } ?: run {
            LOGGER.info("I=Desconto nao encontrado, pointId={}", identity)
            return false
        }
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
