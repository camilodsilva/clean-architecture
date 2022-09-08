package br.com.cleanarchitecture.domain.component.impl

import br.com.cleanarchitecture.domain.component.DiscountComponent
import br.com.cleanarchitecture.domain.entity.Discount
import br.com.cleanarchitecture.domain.exception.EntityNotExistsException
import br.com.cleanarchitecture.domain.pattern.bo.DiscountBO
import br.com.cleanarchitecture.domain.repository.DiscountRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(propagation = Propagation.REQUIRED)
class DiscountComponentImpl(
    private val discountRepository: DiscountRepository
) : DiscountComponent {

    override fun create(businessObject: DiscountBO): DiscountBO {
        LOGGER.info("I=Iniciando persistencia do desconto, discountBO={}", businessObject)
        val created = save(businessObject.toDiscount()).toDiscountBO()

        LOGGER.info("I=Desconto persistido com sucesso, discountBO={}", created)
        return created
    }

    private fun save(discount: Discount): Discount {
        LOGGER.info("I=Persistindo desconto, discount={}", discount)
        val saved = discountRepository.save(discount)

        LOGGER.info("I=Desconto persistido com sucesso, discount={}", discount)
        return saved
    }

    @Transactional(readOnly = true)
    override fun retrieve(identity: Long): DiscountBO? {
        LOGGER.info("I=Iniciando busca do desconto, customerId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Desconto encontrado, customerBO={}", it)
            it.toDiscountBO()
        } ?: run {
            LOGGER.warn("I=Desconto nao encontrado encontrado, customerId={}", identity)
            null
        }
    }

    private fun findById(customerId: Long): Discount? {
        val found = discountRepository.findById(customerId)

        if (found.isEmpty) {
            return null
        }

        return found.get()
    }

    @Throws(EntityNotExistsException::class)
    override fun update(businessObject: DiscountBO): DiscountBO {
        LOGGER.info("I=Iniciando atualizacao do desconto, customerBO={}", businessObject)
        val customerId = businessObject.id ?: throw EntityNotExistsException()

        LOGGER.info("I=Iniciando busca do desconto, customerId={}", customerId)
        val found = findById(customerId)

        return found?.let {
            LOGGER.info("I=Desconto encontrado, customerBO={}", it)

            it.amount = businessObject.amount
            it.points = businessObject.points
            it.status = businessObject.status

            LOGGER.info("I=Iniciando persistencia do desconto, customerBO={}", it)
            val updated = save(it).toDiscountBO()

            LOGGER.info("I=Retornando desconto, customerBO={}", updated)
            return@let updated
        } ?: run {
            LOGGER.warn("I=Desconto nao encontrado encontrado, customerId={}", customerId)
            throw EntityNotExistsException()
        }
    }

    override fun delete(identity: Long): Boolean {
        LOGGER.info("I=Iniciando busca do desconto, customerId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Iniciando exclusao do desconto, customerId={}", identity)
            discountRepository.delete(it)

            LOGGER.info("I=Desconto excluido com sucesso, customerId={}", identity)
            return true
        } ?: run {
            LOGGER.info("I=Desconto nao encontrado, customerId={}", identity)
            return false
        }
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
