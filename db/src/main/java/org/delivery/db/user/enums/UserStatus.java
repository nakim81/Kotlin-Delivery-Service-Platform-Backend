package org.delivery.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    REGISTERED("register"),
    UNREGISTERED("unregister")
    ;

    private final String description;
}
