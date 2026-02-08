package com.shobby.purchase.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseCommand {
    private String orderNumber;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private BigDecimal totalPrice;
    private Long supplierId;
}
