package com.ToDoApi.Service;

import com.ToDoApi.todo.controller.dto.CreateToDoRequest;
import com.ToDoApi.todo.controller.dto.MessageResponse;
import com.ToDoApi.todo.controller.dto.UpdateToDoRequest;
import com.ToDoApi.todo.databases.entity.ToDoList;
import com.ToDoApi.todo.databases.entity.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoListRepository toDoListRepository;

    public MessageResponse createToDo(CreateToDoRequest request){
        toDoListRepository.save(ToDoList.builder()
                    .contents(request.getContents())
                    .build());
        return MessageResponse.builder()
                .message("ToDoList 생성이 완료되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse updateToDo(UpdateToDoRequest dto, Long id){
        ToDoList toDoList = toDoListRepository.findById(id).get();
        toDoList.setContents(dto.getContents());
        return MessageResponse.builder()
                .message(id + "번 아이디 ToDo 수정되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse deleteToDo(Long id){
        toDoListRepository.deleteById(id);
        return MessageResponse.builder()
                .message(id + "번 아이디 ToDo가 삭제되었습니다.")
                .build();
    }
    public List<ToDoList> getToDo(){
        return toDoListRepository.findAll();
    }
    public ToDoList getToDo(Long id){
        return toDoListRepository.findById(id).get();
    }
}
