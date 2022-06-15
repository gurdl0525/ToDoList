package com.ToDoApi.todo.service;

import com.ToDoApi.todo.dto.*;
import com.ToDoApi.todo.entity.Member;
import com.ToDoApi.todo.entity.ToDoList;
import com.ToDoApi.todo.repository.ToDoListRepository;
import com.ToDoApi.todo.exception.BaseException;
import com.ToDoApi.todo.exception.ErrorCode;
import com.ToDoApi.todo.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoListRepository toDoListRepository;
    private final AuthenticationFacade authenticationFacade;
    @Transactional
    public MessageResponse createToDo(CreateToDoRequest request){
        toDoListRepository.save(ToDoList.builder()
                    .contents(request.getContents())
                    .is_success(true)
                    .member(authenticationFacade.getCurrentMember())
                    .build());
        return MessageResponse.builder()
                .message("["+"ToDoList:" + request.getContents() +"]" + "가 생성이 완료되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse updateToDo(UpdateToDoRequest dto, Long id){
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        checkPermission(toDoList);
        toDoList.setContents(dto.getContents());
        return MessageResponse.builder()
                .message(id + "번 TodoList를 수정했습니다.")
                .build();
    }
    @Transactional
    public MessageResponse deleteToDo(Long id){
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        checkPermission(toDoList);
        try {
            toDoList.setIs_success(false);
        } catch (Exception e) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }
        return MessageResponse.builder()
                .message(id + "번 TodoList가 삭제되었습니다.")
                .build();
    }
    public List<ToDoListResponse> getToDo(){
        List<ToDoListResponse> toDoListResponses = new ArrayList<>();
        List<ToDoList> toDoLists = toDoListRepository.findByMember(authenticationFacade.getCurrentMember());
        for (ToDoList todo : toDoLists) {
            if(todo.getIs_success()) {
                toDoListResponses.add(new ToDoListResponse(todo.getId(), todo.getContents()));
            }
        }
//        return toDoLists.stream().map(toDoList -> new ToDoListResponse(toDoList.getContents())).collect(Collectors.toList());
        return toDoListResponses;
    }
    public ToDoListResponse getToDo(Long id){
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        checkPermission(toDoList);
        return new ToDoListResponse(toDoList.getId(), toDoList.getContents());
    }
    private void checkPermission(ToDoList toDoList){
        Member currentMember = authenticationFacade.getCurrentMember();
        if(!toDoList.getMember().equals(currentMember)){
            throw new BaseException(ErrorCode.FORBIDDEN);
        }
    }
}
