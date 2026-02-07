package com.shobby.inventory;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class InventoryNotFoundException extends GeneralException {
    public InventoryNotFoundException() {
        super(ErrorCode.INVENTORY_NOT_FOUND);
    }
}
