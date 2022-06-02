package com.ToDoApi.todo.service;

import com.ToDoApi.todo.dto.MemberRequest;
import com.ToDoApi.todo.dto.MessageResponse;
import com.ToDoApi.todo.entity.Member;
import com.ToDoApi.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MessageResponse join(MemberRequest request){
        duplicateMemberCheck(request.getMemberId());
        Member member = Member.builder()
                .memberId(request.getMemberId())
                .password(request.getMemberPsw())
                .build();
        memberRepository.save(member);
        return MessageResponse.builder()
                .message(member.getMemberId() + "님이 정상적으로 회원가입 완료되었습니다.")
                .build();
    }
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
}
