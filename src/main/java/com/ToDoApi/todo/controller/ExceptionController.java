package com.ToDoApi.todo.controller;

import com.ToDoApi.todo.exception.BaseException;
import com.ToDoApi.todo.exception.ErrorCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;

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
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity unsupportedTypeException() {
        ErrorCode errorCode = ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity badRequest(){
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity duplicateMember() {
        ErrorCode errorCode = ErrorCode.ILLEGAL_STATE_EXCEPTION;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity badRequestType() {
        ErrorCode errorCode = ErrorCode.SQL_INTEGRITY_CONSTRAINT_VIOLATION_EXCEPTION;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity badRequestNullPointId(){
        ErrorCode errorCode = ErrorCode.NOT_FOUND;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
}
