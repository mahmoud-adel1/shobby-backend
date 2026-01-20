package com.shobby.security.config;

import com.shobby.common.exception.CustomException;

public class UsernameNotFoundException extends CustomException {
    public UsernameNotFoundException(String message) {
        super(700, message);
    }
}
