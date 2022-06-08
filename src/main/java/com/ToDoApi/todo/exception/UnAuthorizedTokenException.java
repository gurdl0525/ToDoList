package com.ToDoApi.todo.exception;

import lombok.Getter;

@Getter
public class UnAuthorizedTokenException extends BaseException{
    public UnAuthorizedTokenException() {
        super(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION);
    }
}
