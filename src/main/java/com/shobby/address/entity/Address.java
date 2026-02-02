package com.shobby.address.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "postal_code")
    private Integer postalCode;
}
