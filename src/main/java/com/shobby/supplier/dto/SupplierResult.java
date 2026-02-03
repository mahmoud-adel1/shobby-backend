package com.shobby.supplier.dto;

import com.shobby.address.dto.AddressResult;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SupplierResult {
    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String mobileNumber;
    private AddressResult addressResult;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled;
}
