package com.shobby.supplier.mapper;

import com.shobby.address.mapper.AddressMapper;
import com.shobby.supplier.dto.SupplierCommand;
import com.shobby.supplier.dto.SupplierRequest;
import com.shobby.supplier.dto.SupplierResponse;
import com.shobby.supplier.dto.SupplierResult;
import com.shobby.supplier.entity.Supplier;

public class SupplierMapper {

    public static SupplierCommand toCommand(SupplierRequest supplierRequest) {
        return SupplierCommand
                .builder()
                .companyName(supplierRequest.getCompanyName())
                .contactName(supplierRequest.getContactName())
                .contactTitle(supplierRequest.getContactTitle())
                .mobileNumber(supplierRequest.getMobileNumber())
                .addressCommand(AddressMapper.toCommand(supplierRequest.getAddressRequest()))
                .build();
    }

    public static Supplier toEntity(SupplierCommand supplierCommand) {
        return Supplier
                .builder()
                .companyName(supplierCommand.getCompanyName())
                .contactName(supplierCommand.getContactName())
                .contactTitle(supplierCommand.getContactTitle())
                .mobileNumber(supplierCommand.getMobileNumber())
                .build();
    }

    public static SupplierResult toResult(Supplier supplier) {
        return SupplierResult
                .builder()
                .id(supplier.getId())
                .companyName(supplier.getCompanyName())
                .contactName(supplier.getContactName())
                .contactTitle(supplier.getContactTitle())
                .mobileNumber(supplier.getMobileNumber())
                .addressResult(AddressMapper.toResult(supplier.getAddress()))
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .enabled(supplier.isEnabled())
                .build();
    }

    public static SupplierResponse toResponse(SupplierResult supplierResult) {
        return SupplierResponse
                .builder()
                .id(supplierResult.getId())
                .companyName(supplierResult.getCompanyName())
                .contactName(supplierResult.getContactName())
                .contactTitle(supplierResult.getContactTitle())
                .mobileNumber(supplierResult.getMobileNumber())
                .addressResponse(AddressMapper.toResponse(supplierResult.getAddressResult()))
                .createdAt(supplierResult.getCreatedAt())
                .updatedAt(supplierResult.getUpdatedAt())
                .enabled(supplierResult.isEnabled())
                .build();
    }

    public static void updateSupplier(Supplier supplier, SupplierCommand supplierCommand) {
        supplier.setCompanyName(supplierCommand.getCompanyName());
        supplier.setContactName(supplierCommand.getContactName());
        supplier.setContactTitle(supplierCommand.getContactTitle());
        supplier.setMobileNumber(supplierCommand.getMobileNumber());
    }

}
