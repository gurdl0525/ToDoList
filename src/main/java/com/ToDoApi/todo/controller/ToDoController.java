package com.ToDoApi.todo.controller;

import com.ToDoApi.todo.dto.*;
import com.ToDoApi.todo.service.ToDoService;
import com.ToDoApi.todo.entity.ToDoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createToDo(@RequestBody CreateToDoRequest dto) {
        return toDoService.createToDo(dto);
    }
    @PutMapping("/{todo-id}")
    public MessageResponse updateToDo(@RequestBody UpdateToDoRequest dto, @PathVariable("todo-id") Long id) {
        return toDoService.updateToDo(dto, id);
    }
    @DeleteMapping("/{todo-id}")
    public MessageResponse deleteToDo(@PathVariable("todo-id") Long id) {
        return toDoService.deleteToDo(id);
    }
    @GetMapping
    public List<ToDoListResponse> readAllToDo() {
        return toDoService.getToDo();
    }
    @GetMapping("/{todo-id}")
    public ToDoListResponse readToDo(@PathVariable("todo-id") Long id){
        return toDoService.getToDo(id);
    }
}
