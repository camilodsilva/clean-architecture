package br.com.cleanarchitecture.domain.component

import br.com.cleanarchitecture.domain.pattern.bo.BillBO

interface BillComponent : DomainCrudComponent<BillBO> {
    override fun create(businessObject: BillBO): BillBO
    override fun retrieve(identity: Long): BillBO?
    override fun update(businessObject: BillBO): BillBO
    override fun delete(identity: Long): Boolean
}
