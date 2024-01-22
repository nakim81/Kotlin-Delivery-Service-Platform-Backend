package org.delivery.api.domain.userorder.business

import org.delivery.api.common.Log
import org.delivery.api.domain.store.converter.StoreConverter
import org.delivery.api.domain.store.service.StoreService
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter
import org.delivery.api.domain.storemenu.service.StoreMenuService
import org.delivery.api.domain.user.model.User
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse
import org.delivery.api.domain.userorder.converter.UserOrderConverter
import org.delivery.api.domain.userorder.producer.UserOrderProducer
import org.delivery.api.domain.userorder.service.UserOrderService
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService
import org.delivery.common.annotation.Business
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus
import kotlin.streams.toList

@Business
class UserOrderBusiness(
    private val userOrderService: UserOrderService,
    private val userOrderConverter: UserOrderConverter,

    private val storeMenuService: StoreMenuService,
    private val storeMenuConverter: StoreMenuConverter,

    private val userOrderMenuService: UserOrderMenuService,
    private val userOrderMenuConverter: UserOrderMenuConverter,

    private val storeService: StoreService,
    private val storeConverter: StoreConverter,

    private val userOrderProducer: UserOrderProducer
) {

    companion object: Log

    fun userOrder(
        user: User?,
        body: UserOrderRequest?
    ): UserOrderResponse {
        // find store
        val storeEntity = storeService.getStoreWithThrow(body?.storeId)

        // find order menu
        val storeMenuEntityList = body?.storeMenuIdList
            ?.mapNotNull { storeMenuService.getStoreMenuWithThrow(it) }
            ?.toList()

        // order
        val userOrderEntity = userOrderConverter.toEntity(
            user = user,
            storeEntity = storeEntity,
            storeMenuEntityList = storeMenuEntityList
        ).run {
            userOrderService.order(this)
        }

        // generate user order menu list
        val userOrderMenuEntityList = storeMenuEntityList
            ?.map { it -> userOrderMenuConverter.toEntity(userOrderEntity, it) }
            ?.toList()

        userOrderMenuEntityList?.forEach { userOrderMenuService.order(it) }

        // notify order asynchronously
        userOrderProducer.sendOrder(userOrderEntity)

        return userOrderConverter.toResponse(userOrderEntity)
    }

    fun current(
        user: User?
    ): List<UserOrderDetailResponse> {
        return userOrderService.current(user?.id)
            .map { userOrderEntity ->

                val storeMenuEntityList = userOrderEntity.userOrderMenuList
                    ?.filter {userOrderMenuEntity ->
                        userOrderMenuEntity.status == UserOrderMenuStatus.REGISTERED
                    }?.map { userOrderMenuEntity ->
                        userOrderMenuEntity.storeMenu
                    }?.toList()

                UserOrderDetailResponse(
                    storeResponse = storeConverter.toResponse(userOrderEntity.store),
                    storeMenuResponseList = storeMenuConverter.toResponse(storeMenuEntityList),
                    userOrderResponse = userOrderConverter.toResponse(userOrderEntity)
                )
            }.toList()
    }

    fun history(
        user: User?
    ): List<UserOrderDetailResponse> {
        return userOrderService.history(user?.id)
            .map { userOrderEntity ->

                val storeMenuEntityList = userOrderEntity.userOrderMenuList
                    ?.filter {userOrderMenuEntity ->
                        userOrderMenuEntity.status == UserOrderMenuStatus.REGISTERED
                    }?.map { userOrderMenuEntity ->
                        userOrderMenuEntity.storeMenu
                    }?.toList()

                UserOrderDetailResponse(
                    storeResponse = storeConverter.toResponse(userOrderEntity.store),
                    storeMenuResponseList = storeMenuConverter.toResponse(storeMenuEntityList),
                    userOrderResponse = userOrderConverter.toResponse(userOrderEntity)
                )
            }.toList()
    }

    fun read(
        user: User?,
        orderId: Long?
    ): UserOrderDetailResponse {

        return userOrderService.getUserOrderWithoutStatusWithThrow(orderId, user?.id)
            .let { userOrderEntity ->

                val storeMenuEntityList = userOrderEntity.userOrderMenuList
                    ?.stream()
                    ?.filter { it.status == UserOrderMenuStatus.REGISTERED }
                    ?.map { it.storeMenu }
                    ?.toList()
                    ?: listOf()

                UserOrderDetailResponse(
                    storeResponse = storeConverter.toResponse(userOrderEntity.store),
                    storeMenuResponseList = storeMenuConverter.toResponse(storeMenuEntityList),
                    userOrderResponse = userOrderConverter.toResponse(userOrderEntity)
                )
            }

    }
}