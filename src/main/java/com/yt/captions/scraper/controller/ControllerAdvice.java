package com.yt.captions.scraper.controller;

import com.yt.captions.scraper.exception.ErrorResponse;
import com.yt.captions.scraper.exception.NothingFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by LulzimG on 02/02/17.
 */
@RestControllerAdvice(annotations = {RestController.class})
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> getMessage(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(400);
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NothingFoundException.class)
    public ResponseEntity<ErrorResponse> getNothingFoundMessage(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(202);
        String message = ex.getMessage();
        errorResponse.setMessage(message);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
