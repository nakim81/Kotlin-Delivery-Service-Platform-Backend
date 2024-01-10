package org.delivery.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.delivery.common.error.ErrorCode;
import org.delivery.common.exception.ApiException;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreEntity getStoreWithThrow(Long id){
        var entity = Optional.ofNullable(storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED));
        return entity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreEntity register(StoreEntity storeEntity){
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    it.setStar(0);
                    it.setStatus(StoreStatus.REGISTERED);
                    //TODO: set registered_at
                    return storeRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreEntity> searchByCategory(StoreCategory storeCategory){
        var list = storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, storeCategory);
        return list;
    }

    public List<StoreEntity> registerStore(){
        var list = storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
        return list;
    }
}
