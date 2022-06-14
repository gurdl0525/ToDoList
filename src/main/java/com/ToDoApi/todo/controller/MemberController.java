package com.ToDoApi.todo.controller;

import com.ToDoApi.todo.dto.MemberRequest;
import com.ToDoApi.todo.dto.MessageResponse;
import com.ToDoApi.todo.dto.TokenResponse;
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
    @PostMapping("/login")
    public TokenResponse login(@RequestBody MemberRequest memberRequest) {
        return memberService.login(memberRequest);
    }
    @DeleteMapping("/withdrawal")
    public MessageResponse deleteById(@RequestBody MemberRequest memberRequest){
        return memberService.deleteById(memberRequest);
    }
    @DeleteMapping("/all")
    public MessageResponse deleteAll(){
        return memberService.deleteAll();
    }
}
