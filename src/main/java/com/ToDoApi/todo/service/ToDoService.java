package com.ToDoApi.todo.service;

import com.ToDoApi.todo.dto.CreateToDoRequest;
import com.ToDoApi.todo.dto.MessageResponse;
import com.ToDoApi.todo.dto.UpdateToDoRequest;
import com.ToDoApi.todo.databases.entity.ToDoList;
import com.ToDoApi.todo.databases.entity.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoListRepository toDoListRepository;

    private ToDoList toDoList = new ToDoList();

    @Transactional
    public MessageResponse createToDo(CreateToDoRequest request){
        toDoListRepository.save(ToDoList.builder()
                    .contents(request.getContent())
                    .build());
        return MessageResponse.builder()
                .message("ToDoList 생성이 완료되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse updateToDo(UpdateToDoRequest dto, Long id){
        ToDoList toDoList = toDoListRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException(id + "번 아이디는 없는 아이디 입니다."));
        toDoList.setContents(dto.getContent());
        return MessageResponse.builder()
                .message(id + "번 아이디 ToDo 수정되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse deleteToDo(Long id){
        toDoListRepository.deleteById(id);
        return MessageResponse.builder()
                .build();
    }
    public List<ToDoList> getToDo(){
        return toDoListRepository.findAll();
    }
    public ToDoList getToDo(Long id){
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id + "번 아이디는 없는 아이디 입니다."));
    }
    private void noneIdChecker(Long id){
        toDoListRepository.findById(toDoList.getId())
                .ifPresent(toDoList1 -> {
                    throw new IllegalStateException("예외처리");
                });
    }
}
