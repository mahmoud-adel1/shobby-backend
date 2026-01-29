package com.shobby.product.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class ProductSkuAlreadyExistsException extends GeneralException {
    public ProductSkuAlreadyExistsException() {
        super(ErrorCode.PRODUCT_SKU_ALREADY_EXISTS);
    }
}
