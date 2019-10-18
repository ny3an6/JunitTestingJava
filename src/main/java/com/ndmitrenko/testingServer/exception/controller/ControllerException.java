package com.ndmitrenko.testingServer.exception.controller;

import com.ndmitrenko.testingServer.dto.response.DefaultResponse;
import com.ndmitrenko.testingServer.exception.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException{

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<Object> handleProblemWithShowingCompany(DefaultException ex){
        return new ResponseEntity<>(new DefaultResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

}
