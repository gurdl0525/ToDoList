package com.ToDoApi.todo.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BAD_REQUEST(400, "BAD_REQUEST : 잘못된 입력입니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(400, "BAD_REQUEST : 잘못된 엔드포인트입니다."),
    ILLEGAL_STATE_EXCEPTION(400,"BAD_REQUEST : 중복된 아이디 입니다."),
    SQL_INTEGRITY_CONSTRAINT_VIOLATION_EXCEPTION(400, "BAD_REQUEST : 잘못된 객체 이름입니다."),
    PASSWORD_NOT_MATCHED(400, "BAD_REQUEST : 잘못된 비밀번호 입니다."),
    UN_AUTHORIZED_TOKEN_EXCEPTION(401, "UN_AUTHORIZED_TOKEN_EXCEPTION : 인증되지 않은 토큰입니다."),
    FORBIDDEN(403, "FORBIDDEN : 권한이 없습니다."),
    NOT_FOUND(404, "NOT_FOUND : 없는 아이디 입니다."),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION(415, "BAD_REQUEST : 잘못된 입력 타입입니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");
    private final Integer status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
