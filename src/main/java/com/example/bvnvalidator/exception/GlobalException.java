package com.example.bvnvalidator.exception;


import com.example.bvnvalidator.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BvnInvalidException.class)
    public ResponseEntity<ErrorResponse> handlerForFailedMailException(final BvnInvalidException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        errorResponse.setDebugMessage("This BVN has already been taken");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
