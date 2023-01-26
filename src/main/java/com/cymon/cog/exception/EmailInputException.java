package com.cymon.cog.exception;

public class EmailInputException  extends RuntimeException{
    public EmailInputException() {
        super();
    }

    public EmailInputException(String message) {
        super(message);
    }

    public EmailInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailInputException(Throwable cause) {
        super(cause);
    }

}
