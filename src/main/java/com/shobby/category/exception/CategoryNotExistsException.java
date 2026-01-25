package com.shobby.category.exception;

import com.shobby.common.exception.ErrorCode;
import com.shobby.common.exception.GeneralException;

public class CategoryNotExistsException extends GeneralException {
    public CategoryNotExistsException() {
        super(ErrorCode.CATEGORY_NOT_FOUND);
    }
}
