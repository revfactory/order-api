package kr.revfactory.orderapi.domain.order.service

import kr.revfactory.orderapi.domain.order.Order
import kr.revfactory.orderapi.domain.order.dto.OrderWithPositionDTO
import kr.revfactory.orderapi.domain.order.enums.OrderStatus
import kr.revfactory.orderapi.domain.order.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderManageService(private val orderRepository: OrderRepository) {

    @Transactional
    fun prepare(orderId: Long) {
        val order = orderRepository.findById(orderId).orElseThrow {
            RuntimeException("Order not found")
        }
        order.setPreparing()
        orderRepository.save(order)
    }

    @Transactional
    fun ready(orderId: Long) {
        val order = orderRepository.findById(orderId).orElseThrow {
            RuntimeException("Order not found")
        }
        order.setReady()
        orderRepository.save(order)
    }

    @Transactional
    fun finish(orderId: Long) {
        val order = orderRepository.findById(orderId).orElseThrow {
            RuntimeException("Order not found")
        }
        order.setFinished()
        orderRepository.save(order)
    }

    @Transactional(readOnly = true)
    fun getAllOrders(): List<Order> {
        return orderRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getOrderedOrders(): List<OrderWithPositionDTO> {
        return orderRepository.findByStatusInOrderByDateTime(listOf(OrderStatus.ORDERED))
            .mapIndexed() { index, order ->
                OrderWithPositionDTO(order, index + 1)
            }
    }

    @Transactional(readOnly = true)
    fun getReadyOrders(): List<Order> {
        return orderRepository.findByStatusInOrderByDateTime(listOf(OrderStatus.READY))
    }
}
