package com.shobby.supplier.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class SupplierNotFoundException extends GeneralException {
    public SupplierNotFoundException() {
        super(ErrorCode.SUPPLIER_NOT_FOUND);
    }
}
