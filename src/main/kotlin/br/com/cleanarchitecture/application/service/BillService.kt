package br.com.cleanarchitecture.application.service

import br.com.cleanarchitecture.domain.pattern.dto.entity.BillReceiverDTO

interface BillService {
    fun create(billReceiverDTO: BillReceiverDTO)
}
