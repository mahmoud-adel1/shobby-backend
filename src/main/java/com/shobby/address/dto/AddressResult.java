package com.shobby.address.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddressResult {
    private Long id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String buildingNumber;
    private Integer postalCode;
}
