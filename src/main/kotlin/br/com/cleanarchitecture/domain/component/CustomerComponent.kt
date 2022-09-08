package br.com.cleanarchitecture.domain.component

import br.com.cleanarchitecture.domain.pattern.bo.CustomerBO

interface CustomerComponent: DomainCrudComponent<CustomerBO> {
    override fun create(businessObject: CustomerBO): CustomerBO
    override fun retrieve(identity: Long): CustomerBO?
    override fun update(businessObject: CustomerBO): CustomerBO
    override fun delete(identity: Long): Boolean
}
