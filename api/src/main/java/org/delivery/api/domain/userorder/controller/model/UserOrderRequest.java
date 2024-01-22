package org.delivery.api.domain.userorder.controller.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserOrderRequest {

    @NotNull
    private Long storeId;

    // order
    // user orders a menu
    // user = user in the logged-in session
    // menu id
    @NotNull
    private List<Long> storeMenuIdList;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<Long> getStoreMenuIdList() {
        return storeMenuIdList;
    }

    public void setStoreMenuIdList(List<Long> storeMenuIdList) {
        this.storeMenuIdList = storeMenuIdList;
    }
}
