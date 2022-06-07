package com.ToDoApi.todo.service;

import com.ToDoApi.todo.dto.MemberRequest;
import com.ToDoApi.todo.dto.MessageResponse;
import com.ToDoApi.todo.entity.Member;;
import com.ToDoApi.todo.exception.BaseException;
import com.ToDoApi.todo.exception.ErrorCode;
import com.ToDoApi.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final MemberRepository memberRepository;

    @Transactional
    public MessageResponse join(MemberRequest request){
        duplicateMemberCheck(request.getMemberId());
        Member member = Member.builder()
                .memberId(request.getMemberId())
                .password(passwordEncoder.encode(request.getMemberPsw()))
                .build();
        memberRepository.save(member);
        return MessageResponse.builder()
                .message(member.getMemberId() + "님이 정상적으로 회원가입 완료되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse login(MemberRequest memberRequest){
        try{
            if(passwordEncoder.matches(memberRequest.getMemberPsw(), findByPassword(memberRequest.getMemberId()))){
                return MessageResponse.builder()
                        .message(memberRequest.getMemberId() + "님 로그인 되었습니다.")
                        .build();
            }
        }catch (Exception e){
            throw  new BaseException(ErrorCode.BAD_REQUEST);
        }
        return MessageResponse.builder()
                .message("잘못된 아이디 또는 비밀번호 입니다.")
                .build();
    }
    private String findByPassword(String memberId){
        return memberRepository.findByMemberId(memberId).stream()
                .map(member -> member.getPassword())
                .collect(Collectors.joining());
    }

    @Transactional
    public List<String> findAll() {
        return memberRepository.findAll().stream()
                .map(member -> member.getMemberId())
                .collect(Collectors.toList());
    }
    private void duplicateMemberCheck(String memberId){
        memberRepository.findByMemberId(memberId)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    @Transactional
    public MessageResponse deleteAll(){
        memberRepository.deleteAll();
        return MessageResponse.builder()
                .message("모든 회원이 삭제 되었습니다.")
                .build();
    }
    @Transactional
    public MessageResponse deleteById(Long id){
        memberRepository.deleteById(id);
        return MessageResponse.builder()
                .message("회원님이 정상적으로 탈퇴되었습니다.")
                .build();
    }
}
