package com.cymon.cog.exception;

public class PasswordInputException extends RuntimeException{
    public PasswordInputException() {
        super();
    }

    public PasswordInputException(String message) {
        super(message);
    }

    public PasswordInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordInputException(Throwable cause) {
        super(cause);
    }

}
