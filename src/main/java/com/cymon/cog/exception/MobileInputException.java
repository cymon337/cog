package com.cymon.cog.exception;

public class MobileInputException  extends RuntimeException{
    public MobileInputException() {
        super();
    }

    public MobileInputException(String message) {
        super(message);
    }

    public MobileInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileInputException(Throwable cause) {
        super(cause);
    }
}
