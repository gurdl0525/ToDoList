package com.ToDoApi.todo.service;

import com.ToDoApi.todo.dto.CreateToDoRequest;
import com.ToDoApi.todo.dto.MessageResponse;
import com.ToDoApi.todo.dto.UpdateToDoRequest;
import com.ToDoApi.todo.entity.ToDoList;
import com.ToDoApi.todo.repository.ToDoListRepository;
import com.ToDoApi.todo.exception.BaseException;
import com.ToDoApi.todo.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoListRepository toDoListRepository;

    @Transactional
    public MessageResponse createToDo(CreateToDoRequest request){
        toDoListRepository.save(ToDoList.builder()
                    .contents(request.getContent())
                    .build());
        return MessageResponse.builder()
                .message("["+"ToDoList:" + request.getContent() +"]" + "가 생성이 완료되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse updateToDo(UpdateToDoRequest dto, Long id){
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        toDoList.setContents(dto.getContent());
        return MessageResponse.builder()
                .message(id + "번 아이디 TodoList가 수정되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse deleteToDo(Long id){
        try {
            toDoListRepository.deleteById(id);
        } catch (Exception e) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }
        return MessageResponse.builder()
                .message(id + "번 아이디 TodoList가 삭제되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse deleteAll(){
        toDoListRepository.deleteAll();
        return MessageResponse.builder()
                .message("모든 TodoList가 삭제 되었습니다.")
                .build();
    }
    public List<ToDoList> getToDo(){
        return toDoListRepository.findAll();
    }
    public ToDoList getToDo(Long id){
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
//                .orElseThrow(() -> new NoSuchElementException(id + "번 아이디는 없는 아이디 입니다."));
    }
}
