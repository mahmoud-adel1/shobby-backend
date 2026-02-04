package com.shobby.product.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class ProductDisabledException extends GeneralException {
    public ProductDisabledException() {
        super(ErrorCode.PRODUCT_DISABLED);
    }
}
