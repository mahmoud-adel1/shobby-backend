package com.shobby.inventory.service;

import com.shobby.inventory.InventoryNotFoundException;
import com.shobby.inventory.dto.InventoryCommand;
import com.shobby.inventory.dto.InventoryResult;
import com.shobby.inventory.entity.Inventory;
import com.shobby.inventory.mapper.InventoryMapper;
import com.shobby.inventory.repository.InventoryRepository;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.entity.Product;
import com.shobby.product.exception.ProductNotFoundException;
import com.shobby.product.mapper.ProductMapper;
import com.shobby.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;


    public Page<InventoryResult> getAll(Pageable pageable) {
        return inventoryRepository
                .findAll(pageable)
                .map(InventoryMapper::toResult);
    }


    public InventoryResult getById(Long inventoryId) {
        Inventory inventory = getInventoryByIdOrThrow(inventoryId);
        return InventoryMapper.toResult(inventory);
    }

    public InventoryResult create(InventoryCommand inventoryCommand) {
        Inventory inventory = InventoryMapper.toEntity(inventoryCommand);
        inventory.setProduct(productService.getProductByIdOrThrow(inventoryCommand.getProductId()));
        inventory.setEnabled(true);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toResult(savedInventory);
    }

    public InventoryResult update(Long inventoryId, InventoryCommand inventoryCommand) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);
        InventoryMapper.updateInventory(inventory, inventoryCommand);
        inventory.setProduct(productService.getProductByIdOrThrow(inventoryCommand.getProductId()));
        Inventory updatedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toResult(updatedInventory);
    }

    public InventoryResult disable(Long inventoryId) {
        Inventory inventory = getInventoryByIdOrThrow(inventoryId);
        inventory.setEnabled(false);
        Inventory updatedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toResult(updatedInventory);
    }

    public InventoryResult enable(Long inventoryId) {
        Inventory inventory = getInventoryByIdOrThrow(inventoryId);
        Inventory updatedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toResult(updatedInventory);
    }

    public Inventory getInventoryByIdOrThrow(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);
    }

}
