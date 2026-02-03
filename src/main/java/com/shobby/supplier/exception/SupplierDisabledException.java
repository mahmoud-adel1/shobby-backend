package com.shobby.supplier.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class SupplierDisabledException extends GeneralException {
    public SupplierDisabledException() {
        super(ErrorCode.SUPPLIER_DISABLED);
    }
}
