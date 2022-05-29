package com.ToDoApi.todo.service;

import com.ToDoApi.todo.databases.entity.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToDoConfig {
    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoConfig(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }
}
