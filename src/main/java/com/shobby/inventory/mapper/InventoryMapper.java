package com.shobby.inventory.mapper;

import com.shobby.inventory.dto.InventoryCommand;
import com.shobby.inventory.dto.InventoryRequest;
import com.shobby.inventory.dto.InventoryResponse;
import com.shobby.inventory.dto.InventoryResult;
import com.shobby.inventory.entity.Inventory;

public class InventoryMapper {
    public static InventoryCommand toCommand(InventoryRequest inventoryRequest) {
        return InventoryCommand
                .builder()
                .totalQuantity(inventoryRequest.getTotalQuantity())
                .reservedQuantity(inventoryRequest.getReservedQuantity())
                .reorderThreshold(inventoryRequest.getReorderThreshold())
                .reorderQuantity(inventoryRequest.getReorderQuantity())
                .productId(inventoryRequest.getProductId())
                .build();
    }

    public static Inventory toEntity(InventoryCommand inventoryCommand) {
        return Inventory
                .builder()
                .totalQuantity(inventoryCommand.getTotalQuantity())
                .reservedQuantity(inventoryCommand.getReservedQuantity())
                .reorderThreshold(inventoryCommand.getReorderThreshold())
                .reorderQuantity(inventoryCommand.getReorderQuantity())
                .build();
    }

    public static InventoryResult toResult(Inventory inventory) {
        return InventoryResult
                .builder()
                .id(inventory.getId())
                .totalQuantity(inventory.getTotalQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .reorderThreshold(inventory.getReorderThreshold())
                .reorderQuantity(inventory.getReorderQuantity())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .enabled(inventory.isEnabled())
                .build();
    }

    public static InventoryResponse toResponse(InventoryResult inventoryResult) {
        return InventoryResponse
                .builder()
                .id(inventoryResult.getId())
                .totalQuantity(inventoryResult.getTotalQuantity())
                .reservedQuantity(inventoryResult.getReservedQuantity())
                .reorderThreshold(inventoryResult.getReorderThreshold())
                .reorderQuantity(inventoryResult.getReorderQuantity())
                .createdAt(inventoryResult.getCreatedAt())
                .updatedAt(inventoryResult.getUpdatedAt())
                .enabled(inventoryResult.isEnabled())
                .build();
    }

    public static void updateInventory(Inventory inventory, InventoryCommand inventoryCommand) {
        inventory.setTotalQuantity(inventoryCommand.getTotalQuantity());
        inventory.setReservedQuantity(inventoryCommand.getReservedQuantity());
        inventory.setReorderThreshold(inventoryCommand.getReorderThreshold());
        inventory.setReservedQuantity(inventoryCommand.getReorderQuantity());
    }




}
