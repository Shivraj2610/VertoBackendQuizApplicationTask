package com.verto.quiz.exception;

import com.verto.quiz.custom.ApiResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //ResourceNotFound Exception
    @ExceptionHandler
    public ResponseEntity<ApiResponseMessage> resourceNotFoundException(
            ResourceNotFoundException e
    ){
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message(e.getMessage())
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    //IncorrectRequestExceptionHandler
    @ExceptionHandler
    public ResponseEntity<ApiResponseMessage> incorrectRequestException(
            IncorrectRequestExceptionHandler e
    ){
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message(e.getMessage())
                .success(false)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
