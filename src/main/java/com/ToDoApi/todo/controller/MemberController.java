package com.ToDoApi.todo.controller;

import com.ToDoApi.todo.dto.MemberRequest;
import com.ToDoApi.todo.dto.MessageResponse;
import com.ToDoApi.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createMember(@RequestBody MemberRequest request) {
        return memberService.join(request);
    }
    @GetMapping
    public List<String> findAll(){
        return memberService.findAll();
    }
    @GetMapping("/login")
    public MessageResponse findPassword(@RequestBody MemberRequest memberRequest) {
        return memberService.login(memberRequest);
    }
    @DeleteMapping("/delete/{id}")
    public MessageResponse deleteById(@PathVariable("id") Long id){
        return memberService.deleteById(id);
    }
    @DeleteMapping("/delete")
    public MessageResponse deleteAll(){
        return memberService.deleteAll();
    }
}
