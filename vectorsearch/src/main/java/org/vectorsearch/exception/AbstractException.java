package org.vectorsearch.exception;

public abstract class AbstractException extends RuntimeException {

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
