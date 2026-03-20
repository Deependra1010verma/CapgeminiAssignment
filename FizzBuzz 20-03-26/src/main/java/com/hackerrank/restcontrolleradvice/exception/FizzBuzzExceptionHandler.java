package com.hackerrank.restcontrolleradvice.exception;

import com.hackerrank.restcontrolleradvice.dto.BuzzException;
import com.hackerrank.restcontrolleradvice.dto.FizzBuzzException;
import com.hackerrank.restcontrolleradvice.dto.FizzException;
import com.hackerrank.restcontrolleradvice.dto.GlobalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class FizzBuzzExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(FizzException.class)
  public ResponseEntity<GlobalError> handleFizzException(FizzException exception) {
    return new ResponseEntity<>(
            new GlobalError(exception.getMessage(), exception.getErrorReason()),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  @ExceptionHandler(BuzzException.class)
  public ResponseEntity<GlobalError> handleBuzzException(BuzzException exception) {
    return new ResponseEntity<>(
            new GlobalError(exception.getMessage(), exception.getErrorReason()),
            HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(FizzBuzzException.class)
  public ResponseEntity<GlobalError> handleFizzBuzzException(FizzBuzzException exception) {
    return new ResponseEntity<>(
            new GlobalError(exception.getMessage(), exception.getErrorReason()),
            HttpStatus.INSUFFICIENT_STORAGE
    );
  }
}
