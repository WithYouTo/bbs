package com.nameless.bbs.exception;

/**
 * @Author LHR
 * Create By 2017/8/26
 */
public class ServiceProcessException extends RuntimeException {

    private static final long serialVersionUID = -2294371871422000531L;

    public ServiceProcessException(String message) {
        super(message);
    }

    public ServiceProcessException(String message, Throwable cause) {
        super(message, cause);
    }


}
