package com.qd.exception;

public class UserUpdateNotAllowedException extends RuntimeException {

    public UserUpdateNotAllowedException(String message) {
        super(message);
    }
}
