package org.delivery.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {

    WAITING_FOR_REGISTER("waiting for register"),
    REGISTERED("registered"),
    WAITING_FOR_UNREGISTER("waiting for unregister"),
    UNREGISTERED("unregistered")
    ;

    private String description;

}
