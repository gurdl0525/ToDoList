package com.ToDoApi.todo.controller;

import com.ToDoApi.todo.exception.BaseException;
import com.ToDoApi.todo.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity exception(BaseException error){
        return ResponseEntity.status(error.getErrorCode().getStatus())
                .body(error.getErrorCode().getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity exception(){
        ErrorCode errorCode = ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
}
