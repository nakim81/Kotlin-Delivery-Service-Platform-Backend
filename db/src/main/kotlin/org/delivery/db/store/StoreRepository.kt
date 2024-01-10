package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<StoreEntity, Long> {
    // select * from store where id = ? and status = ? order by id desc limit 1
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreStatus?): StoreEntity?

    // select * from where status = ? order by id desc
    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<StoreEntity>
    fun findAllByStatusAndCategoryOrderByStarDesc(status: StoreStatus?, category: StoreCategory?): List<StoreEntity>

    // select * from store where name = ? and status = ? order by id desc limit 1
    fun findFirstByNameAndStatusOrderByIdDesc(name: String?, status: StoreStatus?): StoreEntity?
}