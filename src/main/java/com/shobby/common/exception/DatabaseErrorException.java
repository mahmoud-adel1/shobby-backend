package com.shobby.common.exception;

public class DatabaseErrorException extends CustomException {

    public DatabaseErrorException(int errorCode, String message) {
        super(errorCode, message);
    }

}
