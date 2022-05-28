package com.ToDoApi.todo.controller;

import com.ToDoApi.Service.ToDoService;
import com.ToDoApi.todo.controller.dto.CreateToDoRequest;
import com.ToDoApi.todo.controller.dto.MessageResponse;
import com.ToDoApi.todo.controller.dto.UpdateToDoRequest;
import com.ToDoApi.todo.databases.entity.ToDoList;
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
    public MessageResponse createToDo(@RequestBody CreateToDoRequest dto){
        return toDoService.createToDo(dto);
    }

    @PutMapping("/{todo-id}")
    public MessageResponse updateToDo(@RequestBody UpdateToDoRequest dto, @PathVariable("todo-id") Long id){
        return toDoService.updateToDo(dto, id);
    }
    @DeleteMapping
    public MessageResponse deleteToDo(@PathVariable("todo-id") Long id){
        return toDoService.deleteToDo(id);
    }
    @GetMapping
    public List<ToDoList> readAllToDo(){
        return toDoService.getToDo();
    }
    @GetMapping
    public ToDoList readToDo(@PathVariable("todo-id") Long id){
        return toDoService.getToDo(id);
    }
}
