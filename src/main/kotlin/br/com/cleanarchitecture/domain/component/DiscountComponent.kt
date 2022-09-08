package br.com.cleanarchitecture.domain.component

import br.com.cleanarchitecture.domain.pattern.bo.DiscountBO

interface DiscountComponent : DomainCrudComponent<DiscountBO> {
    override fun create(businessObject: DiscountBO): DiscountBO
    override fun retrieve(identity: Long): DiscountBO?
    override fun update(businessObject: DiscountBO): DiscountBO
    override fun delete(identity: Long): Boolean
}
