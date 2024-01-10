package org.delivery.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreCategory {
    CHINESE_FOOD("Chinese", "Chinese"),
    WESTERN_FOOD("Western", "Western"),
    KOREAN_FOOD("Korean", "Korean"),
    JAPANESE_FOOD("Japanese", "Japanese"),
    CHICKEN("chicken", "chicken"),
    PIZZA("pizza", "pizza"),
    HAMBURGER("hamburger", "hamburger"),
    COFFEE_TEA("coffee_tea", "coffee_tea")
    ;

    private String display;
    private String description;

}
