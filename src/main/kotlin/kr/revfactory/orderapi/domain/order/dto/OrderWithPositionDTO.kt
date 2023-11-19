package kr.revfactory.orderapi.domain.order.dto

import kr.revfactory.orderapi.domain.order.Order

data class OrderWithPositionDTO(
    val order: Order,
    val position: Int
)
