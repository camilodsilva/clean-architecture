package br.com.cleanarchitecture.domain.component

import br.com.cleanarchitecture.domain.pattern.bo.PointBO

interface PointComponent : DomainCrudComponent<PointBO> {
    override fun create(businessObject: PointBO): PointBO
    override fun retrieve(identity: Long): PointBO?
    override fun update(businessObject: PointBO): PointBO
    override fun delete(identity: Long): Boolean
}
