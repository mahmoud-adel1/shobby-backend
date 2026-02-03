package com.shobby.supplier.dto;

import com.shobby.address.dto.AddressCommand;
import com.shobby.address.entity.Address;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SupplierCommand {
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String mobileNumber;
    private AddressCommand addressCommand;
}
