package com.shobby.address.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class AddressNotFoundException extends GeneralException {
    public AddressNotFoundException() {
        super(ErrorCode.ADDRESS_NOT_FOUND);
    }
}
