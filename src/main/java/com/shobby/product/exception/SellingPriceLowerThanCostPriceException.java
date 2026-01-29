package com.shobby.product.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class SellingPriceLowerThanCostPriceException extends GeneralException {
    public SellingPriceLowerThanCostPriceException() {
        super(ErrorCode.SELLING_PRICE_LOWER_THAN_COST_PRICE);
    }
}
