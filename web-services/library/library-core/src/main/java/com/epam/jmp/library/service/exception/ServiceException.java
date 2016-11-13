package com.epam.jmp.library.service.exception;

/**
 * Created by Alex on 13.11.2016.
 */
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public  ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
