package com.ToDoApi.todo.dto;


import com.ToDoApi.todo.databases.entity.ToDoList;
import com.ToDoApi.todo.databases.entity.ToDoListRepository;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

@AllArgsConstructor
public class ExceptionRequest extends ToDoList {
    private ToDoListRepository toDoListRepository;
    public ToDoList exception(Long id){
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id + "번 아이디는 없는 아이디 입니다."));
    }
}
