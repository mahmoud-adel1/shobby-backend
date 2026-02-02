package com.shobby.address.service;

import com.shobby.address.dto.AddressCommand;
import com.shobby.address.dto.AddressResult;
import com.shobby.address.entity.Address;
import com.shobby.address.exception.AddressNotFoundException;
import com.shobby.address.mapper.AddressMapper;
import com.shobby.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressResult create(AddressCommand addressCommand) {
        Address address = AddressMapper.toEntity(addressCommand);
        Address savedAddress = addressRepository.save(address);
        return AddressMapper.toResult(savedAddress);
    }

    public AddressResult update(Long addressId, AddressCommand addressCommand) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(AddressNotFoundException::new);

        AddressMapper.updateAddress(address, addressCommand);
        Address updatedAddress = addressRepository.save(address);
        return AddressMapper.toResult(updatedAddress);
    }

    public List<AddressResult> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses
                .stream()
                .map(AddressMapper::toResult)
                .toList();
    }

    public AddressResult getById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(AddressNotFoundException::new);
        return AddressMapper.toResult(address);

    }
}
