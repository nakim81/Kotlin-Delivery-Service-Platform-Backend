package org.delivery.api.domain.userorder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {

    @NotNull
    private Long storeId;

    // order
    // user orders a menu
    // user = user in the logged-in session
    // menu id
    @NotNull
    private List<Long> storeMenuIdList;
}
