package org.delivery.db.userorder.enums;

public enum UserOrderStatus {

    REGISTERED("registered"),
    UNREGISTERED("unregistered"),
    ORDER("order"),
    ACCEPT("accept"),
    COOKING("cooking"),
    DELIVERY("delivery"),
    RECEIVE("receive"),
    ;

    UserOrderStatus(String description){
        this.description = description;
    }

    private String description;
}
