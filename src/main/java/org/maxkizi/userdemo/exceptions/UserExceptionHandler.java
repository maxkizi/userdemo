package org.maxkizi.userdemo.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody()
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Response> handleUserNotFoundException(UserNotFoundException e) {
        log.error("User not found");
        return buildResponseEntity(e);
    }

    private ResponseEntity<Response> buildResponseEntity(BaseException e) {
        Response response = new Response(e.getMessage(), e.getCode());
        return new ResponseEntity(response, NOT_FOUND);
    }


}
