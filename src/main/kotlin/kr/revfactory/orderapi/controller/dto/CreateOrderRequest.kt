package kr.revfactory.orderapi.controller.dto

import kr.revfactory.orderapi.domain.order.Customer
import kr.revfactory.orderapi.domain.order.Item

data class CreateOrderRequest(
    val items: List<Item>,
    val customer: Customer
)
