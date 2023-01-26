package com.cymon.cog.exception;

public class GalleryManagerException extends RuntimeException{
    public GalleryManagerException() {
        super();
    }

    public GalleryManagerException(String message) {
        super(message);
    }

    public GalleryManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GalleryManagerException(Throwable cause) {
        super(cause);
    }
}
