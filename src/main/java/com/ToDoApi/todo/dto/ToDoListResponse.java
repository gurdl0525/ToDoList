package com.ToDoApi.todo.dto;

import com.ToDoApi.todo.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
