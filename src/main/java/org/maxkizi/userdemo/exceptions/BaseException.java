package org.maxkizi.userdemo.exceptions;


public class BaseException extends RuntimeException {
    private String code;

    public BaseException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
