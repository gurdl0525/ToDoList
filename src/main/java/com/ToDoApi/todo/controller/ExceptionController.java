package com.ToDoApi.todo.controller;

import com.ToDoApi.todo.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity exception(BaseException error){
        return ResponseEntity.status(error.getErrorCode().getStatus())
                .body(error.getErrorCode().getMessage());
    }
}
