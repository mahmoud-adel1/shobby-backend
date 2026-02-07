package com.shobby.inventory.controller;

import com.shobby.inventory.dto.InventoryCommand;
import com.shobby.inventory.dto.InventoryRequest;
import com.shobby.inventory.dto.InventoryResponse;
import com.shobby.inventory.dto.InventoryResult;
import com.shobby.inventory.mapper.InventoryMapper;
import com.shobby.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<InventoryResponse> getAll(@PageableDefault(direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        Page<InventoryResult> inventoryResults = inventoryService.getAll(pageable);
        return inventoryResults.map(InventoryMapper::toResponse);
    }

    @GetMapping("/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse getById(@PathVariable Long inventoryId) {
        InventoryResult inventoryResult = inventoryService.getById(inventoryId);
        return InventoryMapper.toResponse(inventoryResult);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponse create(@RequestBody @Valid InventoryRequest inventoryRequest) {
        InventoryCommand inventoryCommand = InventoryMapper.toCommand(inventoryRequest);
        InventoryResult inventoryResult = inventoryService.create(inventoryCommand);
        return InventoryMapper.toResponse(inventoryResult);
    }

    @PutMapping("/{inventoryId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponse update(@PathVariable Long inventoryId, @RequestBody @Valid InventoryRequest inventoryRequest) {
        InventoryCommand inventoryCommand = InventoryMapper.toCommand(inventoryRequest);
        InventoryResult inventoryResult = inventoryService.update(inventoryId, inventoryCommand);
        return InventoryMapper.toResponse(inventoryResult);
    }

    @PatchMapping("/{inventoryId}/disable")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponse disable(@PathVariable Long inventoryId) {
        InventoryResult inventoryResult = inventoryService.disable(inventoryId);
        return InventoryMapper.toResponse(inventoryResult);
    }

    @PatchMapping("/{inventoryId}/enable")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponse enable(@PathVariable Long inventoryId) {
        InventoryResult inventoryResult = inventoryService.enable(inventoryId);
        return InventoryMapper.toResponse(inventoryResult);
    }

}
