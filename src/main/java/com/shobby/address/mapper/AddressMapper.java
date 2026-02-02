package com.shobby.address.mapper;

import com.shobby.address.dto.AddressCommand;
import com.shobby.address.dto.AddressRequest;
import com.shobby.address.dto.AddressResponse;
import com.shobby.address.dto.AddressResult;
import com.shobby.address.entity.Address;

public class AddressMapper {
    public static AddressCommand toCommand(AddressRequest addressRequest) {
        return AddressCommand
                .builder()
                .country(addressRequest.getCountry())
                .state(addressRequest.getState())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .buildingNumber(addressRequest.getBuildingNumber())
                .postalCode(addressRequest.getPostalCode())
                .build();
    }

    public static Address toEntity(AddressCommand addressCommand) {
        return Address
                .builder()
                .country(addressCommand.getCountry())
                .state(addressCommand.getState())
                .city(addressCommand.getCity())
                .street(addressCommand.getStreet())
                .buildingNumber(addressCommand.getBuildingNumber())
                .postalCode(addressCommand.getPostalCode())
                .build();
    }

    public static AddressResult toResult(Address address) {
        return AddressResult
                .builder()
                .id(address.getId())
                .country(address.getCountry())
                .state(address.getState())
                .city(address.getCity())
                .street(address.getStreet())
                .buildingNumber(address.getBuildingNumber())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static AddressResponse toResponse(AddressResult addressResult) {
        return AddressResponse
                .builder()
                .id(addressResult.getId())
                .country(addressResult.getCountry())
                .state(addressResult.getState())
                .city(addressResult.getCity())
                .street(addressResult.getStreet())
                .buildingNumber(addressResult.getBuildingNumber())
                .postalCode(addressResult.getPostalCode())
                .build();
    }


    public static void updateAddress(Address address, AddressCommand addressCommand) {
        address.setCountry(addressCommand.getCountry());
        address.setState(addressCommand.getState());
        address.setCity(addressCommand.getCity());
        address.setStreet(addressCommand.getStreet());
        address.setBuildingNumber(addressCommand.getBuildingNumber());
        address.setPostalCode(addressCommand.getPostalCode());
    }
}
