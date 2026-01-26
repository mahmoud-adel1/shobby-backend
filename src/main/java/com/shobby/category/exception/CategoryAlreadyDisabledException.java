package com.shobby.category.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class CategoryAlreadyDisabledException extends GeneralException {
    public CategoryAlreadyDisabledException() {
        super(ErrorCode.CATEGORY_ALREADY_DISABLED);
    }
}
