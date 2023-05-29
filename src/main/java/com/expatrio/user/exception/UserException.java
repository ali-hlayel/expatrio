package com.expatrio.user.exception;

public class UserException extends RuntimeException {
    public UserException(UserError error) {
        super(error.name());
    }
}
