package kr.revfactory.orderapi.domain.order

import jakarta.persistence.Embeddable

@Embeddable
data class Item(
    val name: String,
    val quantity: Int
)
