package org.delivery.common.message.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderMessage {

    private Long userOrderId;
}
