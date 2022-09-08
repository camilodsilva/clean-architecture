package br.com.cleanarchitecture.application.service.impl

import br.com.cleanarchitecture.application.service.BillService
import br.com.cleanarchitecture.domain.component.BillComponent
import br.com.cleanarchitecture.domain.component.CustomerComponent
import br.com.cleanarchitecture.domain.component.DiscountComponent
import br.com.cleanarchitecture.domain.pattern.bo.BillBO
import br.com.cleanarchitecture.domain.pattern.dto.entity.BillReceiverDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
class BillServiceImpl(
    private val billComponent: BillComponent,
    private val customerComponent: CustomerComponent,
    private val discountComponent: DiscountComponent,
): BillService {

    override fun create(billReceiverDTO: BillReceiverDTO) {
        LOGGER.info("I=Buscando cliente, customerId={}", billReceiverDTO.customerId)
        val customerBO = customerComponent.retrieve(billReceiverDTO.customerId)

        LOGGER.info("I=Buscando desconto, discountId={}", billReceiverDTO.discountId)
        val discountBO = billReceiverDTO.discountId?.let { discountComponent.retrieve(it) }

        val billBO = BillBO(
            customerBO = customerBO!!,
            discountBO = discountBO,
            amount = billReceiverDTO.amount,
            amountWithDiscount = BigDecimal.ZERO,
            generatedPoints = 0,
            location = billReceiverDTO.location,
        )
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
