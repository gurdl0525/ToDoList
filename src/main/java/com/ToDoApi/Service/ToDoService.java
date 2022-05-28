package com.ToDoApi.Service;

import com.ToDoApi.todo.databases.entity.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private ToDoListRepository toDoListRepository;
}
