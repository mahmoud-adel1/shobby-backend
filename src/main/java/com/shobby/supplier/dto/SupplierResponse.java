package com.shobby.supplier.dto;

import com.shobby.address.dto.AddressResponse;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SupplierResponse {
    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String mobileNumber;
    private AddressResponse addressResponse;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled;
}
