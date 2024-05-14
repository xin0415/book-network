package com.bookproject.book.exception;

// extends RuntimeException make exception not check, only happen during runtime
public class OperationNotPermittedException extends RuntimeException {
    public OperationNotPermittedException(String msg) {
        super(msg);
    }
}
