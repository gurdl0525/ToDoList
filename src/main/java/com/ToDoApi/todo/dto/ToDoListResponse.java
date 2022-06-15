package com.ToDoApi.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ToDoListResponse {
    private final Long id;
    private final String contents;
//    public ToDoListResponse(String contents) {
//        this.contents = contents;
//    }
}
