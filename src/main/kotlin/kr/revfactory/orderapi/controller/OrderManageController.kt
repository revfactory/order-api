package kr.revfactory.orderapi.controller

import kr.revfactory.orderapi.domain.order.Order
import kr.revfactory.orderapi.domain.order.dto.OrderWithPositionDTO
import kr.revfactory.orderapi.domain.order.service.OrderManageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/orders")
class OrderManageController(private val orderManageService: OrderManageService) {
    @GetMapping
    fun getAllOrders(): List<Order> {
        return orderManageService.getAllOrders()
    }

    @GetMapping("/ordered")
    fun getOrderedOrders(): List<OrderWithPositionDTO> {
        return orderManageService.getOrderedOrders()
    }

    @GetMapping("/ready")
    fun getReadyOrders(): List<Order> {
        return orderManageService.getReadyOrders()
    }

    @PutMapping("/{orderId}/prepare")
    fun markPrepare(@PathVariable orderId: Long) {
        orderManageService.prepare(orderId)
    }

    @PutMapping("/{orderId}/ready")
    fun markReady(@PathVariable orderId: Long) {
        orderManageService.ready(orderId)
    }

    @PutMapping("/{orderId}/finish")
    fun markFinish(@PathVariable orderId: Long) {
        orderManageService.finish(orderId)
    }
}
