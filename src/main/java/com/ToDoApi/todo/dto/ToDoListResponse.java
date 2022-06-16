package com.ToDoApi.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToDoListResponse {
    private final Long id;
    private final String contents;
//    public ToDoListResponse(Long id, String contents) {
//        this.id = id;
//        this.contents = contents;
//    }
}
