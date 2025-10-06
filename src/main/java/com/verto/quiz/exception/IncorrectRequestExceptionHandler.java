package com.verto.quiz.exception;

public class IncorrectRequestExceptionHandler extends RuntimeException{

    public IncorrectRequestExceptionHandler(){
        super();
    }

    public IncorrectRequestExceptionHandler(String message){
        super(message);
    }
}
