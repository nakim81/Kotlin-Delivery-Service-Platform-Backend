package org.delivery.db.storeuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserStatus {
    REGISTERED("registered"),
    UNREGISTERED("unregistered")
    ;

    private String description;
}
