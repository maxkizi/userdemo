package org.maxkizi.userdemo.exceptions;

public class AddVacationException extends RuntimeException{
    public AddVacationException(){
        super("Can not add vacation");
    }
}
