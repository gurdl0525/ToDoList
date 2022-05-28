package com.ToDoApi.todo.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponse {
    private final String message;
}
