package org.maxkizi.userdemo.exceptions;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not found");
    }
}
