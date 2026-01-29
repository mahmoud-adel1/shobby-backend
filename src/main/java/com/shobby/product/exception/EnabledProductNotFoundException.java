package com.shobby.product.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class EnabledProductNotFoundException extends GeneralException {
    public EnabledProductNotFoundException() {
        super(ErrorCode.ENABLED_PRODUCT_NOT_FOUND);
    }
}
