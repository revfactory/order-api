package kr.revfactory.orderapi.domain.order

import jakarta.persistence.Embeddable

@Embeddable
class Customer(
    val ldapId: String
)
