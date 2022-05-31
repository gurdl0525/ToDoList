package com.ToDoApi.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public enum ErrorCode {
    NOT_FOUND(404, "Not Found Id."),
    BAD_REQUEST(400, "Bad Request.");

    private Integer status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
