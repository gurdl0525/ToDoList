package com.ToDoApi.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public enum ErrorCode {
    NOT_FOUND(404, "Not Found Id : 없는 아이디 입니다."),
    BAD_REQUEST(400, "Bad Request : 잘못된 입력입니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(400, "잘못된 엔드 포인트 입니다.");
    private Integer status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
