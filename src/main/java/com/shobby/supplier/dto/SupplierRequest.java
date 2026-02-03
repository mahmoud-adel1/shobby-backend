package com.shobby.supplier.dto;

import com.shobby.address.dto.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SupplierRequest {

    @NotBlank(message = "COMPANY_NAME_REQUIRED")
    private String companyName;

    @NotBlank(message = "CONTACT_NAME_REQUIRED")
    private String contactName;

    @NotBlank(message = "CONTACT_TITLE_REQUIRED")
    private String contactTitle;

    @NotBlank(message = "MOBILE_NUMBER_REQUIRED")
    @Pattern( regexp = "^(\\+20|0)?1[0125][0-9]{8}$", message = "INVALID_EGYPTIAN_MOBILE" )
    private String mobileNumber;

    @NotNull(message = "ADDRESS_REQUEST_REQUIRED")
    @Valid
    private AddressRequest addressRequest;
}
