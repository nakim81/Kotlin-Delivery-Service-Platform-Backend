package org.delivery.api.domain.userorder.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.delivery.common.annotation.UserSession;
import org.delivery.common.api.Api;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-order")
@RequiredArgsConstructor
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Valid
            @RequestBody Api<UserOrderRequest> userOrderRequest,

            @Parameter(hidden = true)
            @UserSession User user
    ){
        var response = userOrderBusiness.userOrder(
                user,
                userOrderRequest.getBody());
        return Api.OK(response);
    }

    // current order request
    @GetMapping("/current")
    public Api<List<UserOrderDetailResponse>> current(
            @UserSession
            @Parameter(hidden = true)
            User user
    ){
        var response = userOrderBusiness.current(user);
        return Api.OK(response);
    }

    // past order requests
    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> history(
            @UserSession
            @Parameter(hidden = true)
            User user
    ){
        var response = userOrderBusiness.history(user);
        return Api.OK(response);
    }

    // one order request
    public Api<UserOrderDetailResponse> read(
            @UserSession
            @Parameter(hidden = true)
            User user,

            @PathVariable Long orderId
    ){
        var response = userOrderBusiness.read(user, orderId);
        return Api.OK(response);
    }

}
