package com.shobby.supplier.controller;

import com.shobby.supplier.dto.SupplierCommand;
import com.shobby.supplier.dto.SupplierRequest;
import com.shobby.supplier.dto.SupplierResponse;
import com.shobby.supplier.dto.SupplierResult;
import com.shobby.supplier.mapper.SupplierMapper;
import com.shobby.supplier.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SupplierResponse> getAllSuppliers(
            @PageableDefault(direction = Sort.Direction.ASC, sort = "id") Pageable pageable,
            @RequestParam(defaultValue = "false") boolean enabledOnly) {

        Page<SupplierResult> supplierResults;
        if (enabledOnly) {
            supplierResults = supplierService.getAllEnabled(pageable);
        } else {
            supplierResults = supplierService.getAll(pageable);
        }

        return supplierResults
                .map(SupplierMapper::toResponse);
    }

    @GetMapping("/{supplierId}")
    @ResponseStatus(HttpStatus.OK)
    public SupplierResponse getSupplierById(@PathVariable Long supplierId,
                                            @RequestParam(defaultValue = "false") boolean enabledOnly) {

        SupplierResult supplierResult;
        if (enabledOnly) {
            supplierResult = supplierService.getEnabledSupplierById(supplierId);
        } else {
            supplierResult = supplierService.getSupplierById(supplierId);
        }
        return SupplierMapper.toResponse(supplierResult);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierResponse create(@Valid @RequestBody SupplierRequest supplierRequest) {
        SupplierCommand supplierCommand = SupplierMapper.toCommand(supplierRequest);
        SupplierResult supplierResult = supplierService.create(supplierCommand);
        return SupplierMapper.toResponse(supplierResult);
    }

    @PutMapping("/{supplierId}")
    @ResponseStatus(HttpStatus.OK)
    public SupplierResponse update(@PathVariable Long supplierId, @Valid @RequestBody SupplierRequest supplierRequest) {
        SupplierCommand supplierCommand = SupplierMapper.toCommand(supplierRequest);
        SupplierResult supplierResult = supplierService.update(supplierId, supplierCommand);
        return SupplierMapper.toResponse(supplierResult);
    }

    @PatchMapping("/{supplierId}/disable")
    @ResponseStatus(HttpStatus.OK)
    public SupplierResponse disable(@PathVariable Long supplierId) {
        SupplierResult supplierResult = supplierService.disable(supplierId);
        return SupplierMapper.toResponse(supplierResult);
    }

    @PatchMapping("/{supplierId}/enable")
    @ResponseStatus(HttpStatus.OK)
    public SupplierResponse enable(@PathVariable Long supplierId) {
        SupplierResult supplierResult = supplierService.enable(supplierId);
        return SupplierMapper.toResponse(supplierResult);
    }

}
