package kr.revfactory.orderapi.domain.order.repository

import kr.revfactory.orderapi.domain.order.Order
import kr.revfactory.orderapi.domain.order.enums.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long> {
    fun findByIdAndCustomerLdapId(id: Long, ldapId: String): Order?
    fun findByCustomerLdapIdAndStatusIn(ldapId: String, orderStatusList: List<OrderStatus>): List<Order>
    fun findByStatusInOrderByDateTime(orderStatusList: List<OrderStatus>): List<Order>
    fun countByIdLessThanEqualAndStatusIn(id: Long, orderStatusList: List<OrderStatus>): Int
}
