package com.ToDoApi.todo.security.auth;

import com.ToDoApi.todo.entity.Member;
import com.ToDoApi.todo.exception.BaseException;
import com.ToDoApi.todo.exception.ErrorCode;
import com.ToDoApi.todo.repository.MemberRepository;
import com.ToDoApi.todo.security.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final MemberRepository memberRepository;
    public Member getCurrentMember(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        return memberRepository.findByAccountId(authDetails.getUsername())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
    }
}
