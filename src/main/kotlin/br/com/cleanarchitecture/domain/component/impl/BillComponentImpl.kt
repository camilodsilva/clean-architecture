package br.com.cleanarchitecture.domain.component.impl

import br.com.cleanarchitecture.domain.component.BillComponent
import br.com.cleanarchitecture.domain.entity.Bill
import br.com.cleanarchitecture.domain.exception.EntityNotExistsException
import br.com.cleanarchitecture.domain.pattern.bo.BillBO
import br.com.cleanarchitecture.domain.repository.BillRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(propagation = Propagation.REQUIRED)
class BillComponentImpl(
    private val billRepository: BillRepository,
) : BillComponent {

    override fun create(businessObject: BillBO): BillBO {
        LOGGER.info("I=Iniciando persistencia da cobranca, billBO={}", businessObject)
        val created = save(businessObject.toBill()).toBillBO()

        LOGGER.info("I=Cobranca persistida com sucesso, billBO={}", created)
        return created
    }

    private fun save(bill: Bill): Bill {
        LOGGER.info("I=Persistindo cobranca, bill={}", bill)
        val saved = billRepository.save(bill)

        LOGGER.info("I=Cobranca persistida com sucesso, bill={}", bill)
        return saved
    }

    @Transactional(readOnly = true)
    override fun retrieve(identity: Long): BillBO? {
        LOGGER.info("I=Iniciando busca da cobranca, billId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Cliente encontrado, customerBO={}", it)
            it.toBillBO()
        } ?: run {
            LOGGER.warn("I=Cobranca nao encontrada, billId={}", identity)
            null
        }
    }

    private fun findById(billId: Long): Bill? {
        val found = billRepository.findById(billId)

        if (found.isEmpty) {
            return null
        }

        return found.get()
    }

    @Throws(EntityNotExistsException::class)
    override fun update(businessObject: BillBO): BillBO {
        LOGGER.info("I=Iniciando atualizacao da cobranca, billBO={}", businessObject)
        val billId = businessObject.id ?: throw EntityNotExistsException()

        LOGGER.info("I=Iniciando busca da cobranca, billId={}", billId)
        val found = findById(billId)

        return found?.let {
            LOGGER.info("I=Cliente encontrado, customerBO={}", it)

            it.amount = businessObject.amount
            it.amountWithDiscount = businessObject.amountWithDiscount
            it.generatedPoints = businessObject.generatedPoints
            it.location = businessObject.location

            LOGGER.info("I=Iniciando persistencia da cobranca, billBO={}", it)
            val updated = save(it).toBillBO()

            LOGGER.info("I=Retornando cobranca, billBO={}", updated)
            return@let updated
        } ?: run {
            LOGGER.warn("I=Cobranca nao encontrada, billId={}", billId)
            throw EntityNotExistsException()
        }
    }

    override fun delete(identity: Long): Boolean {
        LOGGER.info("I=Iniciando busca da cobranca, billId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Iniciando exclusao do cliente, billId={}", identity)
            billRepository.delete(it)

            LOGGER.info("I=Cliente excluido com sucesso, billId={}", identity)
            return true
        } ?: run {
            LOGGER.info("I=Cliente nao encontrado, billId={}", identity)
            return false
        }
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
