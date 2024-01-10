package org.delivery.db.storemenu

import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreMenuRepository : JpaRepository<StoreMenuEntity, Long> {
    // select * from store_menu where id = ? and status = ? order by id desc limit 1
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreMenuStatus?): StoreMenuEntity?

    // select * from store_menu where store_id = ? and status = ? order by sequence desc;
    fun findAllByStoreIdAndStatusOrderBySequenceDesc(storeId: Long?, status: StoreMenuStatus?): List<StoreMenuEntity>
}