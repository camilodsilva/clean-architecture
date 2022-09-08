package br.com.cleanarchitecture.domain.component

interface DomainCrudComponent<T> {
    fun create(businessObject: T): T
    fun retrieve(identity: Long): T?
    fun update(businessObject: T): T
    fun delete(identity: Long): Boolean
}
