package com.eliseev.app.exception.custom;

public class NotUniqueTicket extends RuntimeException {

    public NotUniqueTicket() {
        super();
    }

    public NotUniqueTicket(String message) {
        super(message);
    }

    public NotUniqueTicket(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueTicket(Throwable cause) {
        super(cause);
    }

    protected NotUniqueTicket(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
