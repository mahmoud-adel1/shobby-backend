package com.shobby.inventory.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class InventoryCommand {
    private int totalQuantity;
    private int reservedQuantity;
    private int reorderThreshold;
    private int reorderQuantity;
    private Long productId;
}
