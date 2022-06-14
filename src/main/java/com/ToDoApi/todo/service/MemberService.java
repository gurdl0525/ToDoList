package com.ToDoApi.todo.service;

import com.ToDoApi.todo.dto.MemberRequest;
import com.ToDoApi.todo.dto.MessageResponse;
import com.ToDoApi.todo.dto.TokenResponse;
import com.ToDoApi.todo.entity.Member;;
import com.ToDoApi.todo.exception.BaseException;
import com.ToDoApi.todo.exception.ErrorCode;
import com.ToDoApi.todo.repository.MemberRepository;
import com.ToDoApi.todo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public MessageResponse join(MemberRequest request){
        if(request.getAccountId() != null){
            duplicateMemberCheck(request.getAccountId());
            Member member = Member.builder()
                    .accountId(request.getAccountId())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            memberRepository.save(member);
            return MessageResponse.builder()
                    .message(member.getAccountId() + "님이 정상적으로 회원가입 완료되었습니다.")
                    .build();
        } else {
            return MessageResponse.builder()
                    .message("아이디를 입력해주세요.")
                    .build();
        }
    }
    @Transactional
    public TokenResponse login(MemberRequest memberRequest){
        if(passwordEncoder.matches(memberRequest.getPassword(), findByPassword(memberRequest.getAccountId()))){
            return TokenResponse.builder()
                    .accessToken(jwtTokenProvider.generateAccessToken(memberRequest.getAccountId()))
                    .build();
        }
        throw new BaseException(ErrorCode.PASSWORD_NOT_MATCHED);
    }
    private String findByPassword(String accountId){
        return memberRepository.findByAccountId(accountId).stream()
                .map(Member::getPassword)
                .collect(Collectors.joining());
    }
    @Transactional
    public List<String> findAll() {
        return memberRepository.findAll().stream()
                .map(Member::getAccountId)
                .collect(Collectors.toList());
    }
    private void duplicateMemberCheck(String accountId){
        memberRepository.findByAccountId(accountId)
                .ifPresent(m -> {
                    throw new IllegalStateException();
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
    public MessageResponse deleteById(MemberRequest memberRequest){
        if(passwordEncoder.matches(memberRequest.getPassword(), findByPassword(memberRequest.getAccountId()))){
            Member member = memberRepository.findByAccountId(memberRequest.getAccountId()).orElseThrow();
            memberRepository.deleteById(member.getId());
            return MessageResponse.builder()
                    .message("회원님이 정상적으로 탈퇴되었습니다.")
                    .build();
        }
        throw new BaseException(ErrorCode.PASSWORD_NOT_MATCHED);
    }
}
