package com.shobby.address.controller;

import com.shobby.address.dto.AddressCommand;
import com.shobby.address.dto.AddressRequest;
import com.shobby.address.dto.AddressResponse;
import com.shobby.address.dto.AddressResult;
import com.shobby.address.mapper.AddressMapper;
import com.shobby.address.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponse> getAll() {
        List<AddressResult> addressResults = addressService.getAll();
        return addressResults
                .stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @GetMapping("/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse getById(@PathVariable Long addressId) {
        AddressResult addressResult = addressService.getById(addressId);
        return AddressMapper.toResponse(addressResult);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse create(AddressRequest addressRequest) {
        AddressCommand addressCommand = AddressMapper.toCommand(addressRequest);
        AddressResult addressResult = addressService.create(addressCommand);
        return AddressMapper.toResponse(addressResult);
    }

    @PutMapping("/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse update(@PathVariable Long addressId, @Valid @RequestBody AddressRequest addressRequest) {
        AddressCommand addressCommand = AddressMapper.toCommand(addressRequest);
        AddressResult addressResult = addressService.update(addressId, addressCommand);
        return AddressMapper.toResponse(addressResult);
    }


}
