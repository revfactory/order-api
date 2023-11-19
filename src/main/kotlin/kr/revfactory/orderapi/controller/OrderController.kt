package kr.revfactory.orderapi.controller

import kr.revfactory.orderapi.controller.dto.CreateOrderRequest
import kr.revfactory.orderapi.domain.order.Order
import kr.revfactory.orderapi.domain.order.dto.OrderWithPositionDTO
import kr.revfactory.orderapi.domain.order.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody request: CreateOrderRequest): Order {
        return orderService.createOrder(request.customer, request.items)
    }

    @GetMapping
    fun getOrdersByCustomerName(@RequestParam ldapId: String): List<OrderWithPositionDTO> {
        return orderService.getOrdersByCustomerId(ldapId)
    }

    @PutMapping("/{orderId}/cancel")
    fun markPrepare(@PathVariable orderId: Long, @RequestParam ldapId: String) {
        orderService.cancel(orderId, ldapId)
    }
}
