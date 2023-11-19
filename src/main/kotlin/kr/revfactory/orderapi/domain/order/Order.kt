package kr.revfactory.orderapi.domain.order

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import kr.revfactory.orderapi.domain.order.enums.OrderStatus
import java.time.LocalDateTime

@Table(name = "elina_order")
@Entity
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Embedded
    val customer: Customer,

    @ElementCollection
    val items: List<Item>,

    @NotNull
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDERED,

    val dateTime: LocalDateTime = LocalDateTime.now()
) {
    fun setPreparing() {
        if (status == OrderStatus.ORDERED) {
            status = OrderStatus.PREPARING
        } else {
            throw RuntimeException("Invalid Status")
        }
    }

    fun setReady() {
        if (status == OrderStatus.PREPARING) {
            status = OrderStatus.READY
        } else {
            throw RuntimeException("Invalid Status")
        }
    }

    fun setFinished() {
        if (status == OrderStatus.READY) {
            status = OrderStatus.FINISHED
        } else {
            throw RuntimeException("Invalid Status")
        }
    }

    fun setCancel() {
        if (status == OrderStatus.ORDERED) {
            status = OrderStatus.CANCELLED
        } else {
            throw RuntimeException("Invalid Status")
        }
    }
}
