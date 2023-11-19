package kr.revfactory.orderapi.domain.order.service

import jakarta.persistence.EntityNotFoundException
import kr.revfactory.orderapi.domain.order.Customer
import kr.revfactory.orderapi.domain.order.Item
import kr.revfactory.orderapi.domain.order.Order
import kr.revfactory.orderapi.domain.order.dto.OrderWithPositionDTO
import kr.revfactory.orderapi.domain.order.enums.OrderStatus
import kr.revfactory.orderapi.domain.order.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(private val orderRepository: OrderRepository) {

    @Transactional
    fun createOrder(customer: Customer, items: List<Item>): Order {
        val order = Order(customer = customer, items = items)
        return orderRepository.save(order)
    }

    @Transactional(readOnly = true)
    fun getOrdersByCustomerId(ldapId: String): List<OrderWithPositionDTO> {
        return orderRepository.findByCustomerLdapIdAndStatusIn(ldapId, listOf(OrderStatus.ORDERED, OrderStatus.PREPARING, OrderStatus.READY))
            .map {
                OrderWithPositionDTO(it, orderRepository.countByIdLessThanEqualAndStatusIn(it.id, listOf(OrderStatus.ORDERED, OrderStatus.PREPARING)))
            }
    }

    @Transactional
    fun cancel(orderId: Long, ldapId: String) {
        orderRepository.findByIdAndCustomerLdapId(orderId, ldapId)?.let {
            it.setCancel()
            orderRepository.save(it)
        } ?: throw EntityNotFoundException("Order not found")
    }
}
