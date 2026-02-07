package com.shobby.inventory.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class InventoryResult {
    private Long id;
    private int totalQuantity;
    private int reservedQuantity;
    private int reorderThreshold;
    private int reorderQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled;
    private Long productId;
}
