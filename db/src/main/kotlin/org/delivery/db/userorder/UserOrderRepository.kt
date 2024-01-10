package org.delivery.db.userorder

import org.delivery.db.userorder.enums.UserOrderStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderRepository : JpaRepository<UserOrderEntity, Long> {

    fun findAllByUserIdAndStatusOrderByIdDesc(userId: Long?, status: UserOrderStatus?): List<UserOrderEntity>

    fun findAllByUserIdAndStatusInOrderByIdDesc(userId: Long?, status: List<UserOrderStatus>?): List<UserOrderEntity>

    fun findAllByIdAndStatusAndUserId(id: Long?, status: UserOrderStatus?, userId: Long?): UserOrderEntity?

    fun findAllByIdAndUserId(id: Long?, userId: Long?): UserOrderEntity?
}