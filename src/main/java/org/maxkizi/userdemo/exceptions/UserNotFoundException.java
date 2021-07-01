package org.maxkizi.userdemo.exceptions;

import java.util.function.Supplier;

public class UserNotFoundException extends BaseException {
    private static final String CODE = "1";

    public UserNotFoundException() {
        super("User not found", CODE);
    }


}
