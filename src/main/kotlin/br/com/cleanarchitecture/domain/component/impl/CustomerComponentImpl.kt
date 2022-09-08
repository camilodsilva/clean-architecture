package br.com.cleanarchitecture.domain.component.impl

import br.com.cleanarchitecture.domain.component.CustomerComponent
import br.com.cleanarchitecture.domain.entity.Customer
import br.com.cleanarchitecture.domain.exception.EntityNotExistsException
import br.com.cleanarchitecture.domain.pattern.bo.CustomerBO
import br.com.cleanarchitecture.domain.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(propagation = Propagation.REQUIRED)
class CustomerComponentImpl(
    private val customerRepository: CustomerRepository,
) : CustomerComponent{

    override fun create(businessObject: CustomerBO): CustomerBO {
        LOGGER.info("I=Iniciando persistencia do cliente, customerBO={}", businessObject)
        val created = save(businessObject.toCustomer()).toCustomerBO()

        LOGGER.info("I=Cliente persistido com sucesso, customerBO={}", created)
        return created
    }

    private fun save(customer: Customer): Customer {
        LOGGER.info("I=Persistindo cliente, customer={}", customer)
        val saved = customerRepository.save(customer)

        LOGGER.info("I=Cliente persistido com sucesso, bill={}", customer)
        return saved
    }

    @Transactional(readOnly = true)
    override fun retrieve(identity: Long): CustomerBO? {
        LOGGER.info("I=Iniciando busca do cliente, customerId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Cliente encontrado, customerBO={}", it)
            it.toCustomerBO()
        } ?: run {
            LOGGER.warn("I=Cliente nao encontrado encontrado, customerId={}", identity)
            null
        }
    }

    private fun findById(customerId: Long): Customer? {
        val found = customerRepository.findById(customerId)

        if (found.isEmpty) {
            return null
        }

        return found.get()
    }

    @Throws(EntityNotExistsException::class)
    override fun update(businessObject: CustomerBO): CustomerBO {
        LOGGER.info("I=Iniciando atualizacao do cliente, customerBO={}", businessObject)
        val customerId = businessObject.id ?: throw EntityNotExistsException()

        LOGGER.info("I=Iniciando busca do cliente, customerId={}", customerId)
        val found = findById(customerId)

        return found?.let {
            LOGGER.info("I=Cliente encontrado, customerBO={}", it)

            it.email = businessObject.email
            it.pass = businessObject.pass
            it.name = businessObject.name
            it.lastName = businessObject.lastName

            LOGGER.info("I=Iniciando persistencia do cliente, customerBO={}", it)
            val updated = save(it).toCustomerBO()

            LOGGER.info("I=Retornando cliente, customerBO={}", updated)
            return@let updated
        } ?: run {
            LOGGER.warn("I=Cliente nao encontrado encontrado, customerId={}", customerId)
            throw EntityNotExistsException()
        }
    }

    override fun delete(identity: Long): Boolean {
        LOGGER.info("I=Iniciando busca do cliente, customerId={}", identity)
        val found = findById(identity)

        return found?.let {
            LOGGER.info("I=Iniciando exclusao do cliente, customerId={}", identity)
            customerRepository.delete(it)

            LOGGER.info("I=Cliente excluido com sucesso, customerId={}", identity)
            return true
        } ?: run {
            LOGGER.info("I=Cliente nao encontrado, customerId={}", identity)
            return false
        }
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
