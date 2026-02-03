package com.shobby.supplier.service;

import com.shobby.address.entity.Address;
import com.shobby.address.mapper.AddressMapper;
import com.shobby.supplier.dto.SupplierCommand;
import com.shobby.supplier.dto.SupplierResult;
import com.shobby.supplier.entity.Supplier;
import com.shobby.supplier.exception.SupplierDisabledException;
import com.shobby.supplier.exception.SupplierNotFoundException;
import com.shobby.supplier.mapper.SupplierMapper;
import com.shobby.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public Page<SupplierResult> getAll(Pageable pageable) {
        Page<Supplier> suppliers = supplierRepository.findAll(pageable);
        return suppliers.map(SupplierMapper::toResult);
    }

    public Page<SupplierResult> getAllEnabled(Pageable pageable) {
        Page<Supplier> enabledSuppliers = supplierRepository.findAllByEnabledTrue(pageable);
        return enabledSuppliers.map(SupplierMapper::toResult);
    }


    public SupplierResult getSupplierById(Long supplierId) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        return SupplierMapper.toResult(supplier);
    }

    public SupplierResult getEnabledSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findByIdAndEnabledTrue(supplierId)
                .orElseThrow(SupplierDisabledException::new);
        return SupplierMapper.toResult(supplier);
    }


    public SupplierResult create(SupplierCommand supplierCommand) {
        Supplier supplier = SupplierMapper.toEntity(supplierCommand);
        Address address = AddressMapper.toEntity(supplierCommand.getAddressCommand());
        supplier.setAddress(address);
        supplier.setEnabled(true);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return SupplierMapper.toResult(savedSupplier);
    }

    public SupplierResult update(Long supplierId, SupplierCommand supplierCommand) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        SupplierMapper.updateSupplier(supplier, supplierCommand);
        if (supplierCommand.getAddressCommand() != null) {
            AddressMapper.updateAddress(supplier.getAddress(), supplierCommand.getAddressCommand());
        }
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return SupplierMapper.toResult(updatedSupplier);

    }

    public SupplierResult disable(Long supplierId) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        supplier.setEnabled(false);
        Supplier disabledSupplier = supplierRepository.save(supplier);
        return SupplierMapper.toResult(disabledSupplier);
    }

    public SupplierResult enable(Long supplierId) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        supplier.setEnabled(true);
        Supplier enabledSupplier = supplierRepository.save(supplier);
        return SupplierMapper.toResult(enabledSupplier);
    }

    public Supplier getSupplierOrThrow(Long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(SupplierNotFoundException::new);
    }


}
