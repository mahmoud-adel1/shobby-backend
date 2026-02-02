package com.shobby.address.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressCommand {
    private String country;
    private String state;
    private String city;
    private String street;
    private String buildingNumber;
    private Integer postalCode;
}
