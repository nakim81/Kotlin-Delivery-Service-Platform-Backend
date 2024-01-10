package org.delivery.db.storeuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserRole {
    MASTER("master"),
    ADMIN("admin"),
    USER("user")
    ;

    private String description;
}
