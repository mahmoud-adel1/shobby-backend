package com.shobby.category.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class CategoryAlreadyExistsException extends GeneralException {
    public CategoryAlreadyExistsException() {
        super(ErrorCode.CATEGORY_ALREADY_EXISTS);
    }
}
