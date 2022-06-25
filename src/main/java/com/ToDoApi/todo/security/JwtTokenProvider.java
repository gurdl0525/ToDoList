package com.ToDoApi.todo.security;

import com.ToDoApi.todo.exception.UnAuthorizedTokenException;
import com.ToDoApi.todo.security.auth.AuthDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("${key}")
    private String JWT_SECRET;
    private final AuthDetailsService authDetailsService;
    public String getBearerToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null){
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getUserId(String token){
        try {
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e){
            throw new UnAuthorizedTokenException();
        }
    }

    public String generateAccessToken(String accountId){
        return Jwts.builder()
                .setIssuedAt(new Date())
                // 토큰 생성 시간
                .setExpiration(new Date(System.currentTimeMillis() + 720000000))
                // 토큰 만료 시간을 생성 시간으로부터 7200000밀리초 후로 설정(지정)
                .setSubject(accountId)
                // 토큰의 서브젝트를 id값으로 셋팅
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                // SignKey에 시크릿 키를 셋팅해준다
                .compact();
                // 토큰 생성
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = authDetailsService.loadUserByUsername(getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", null);
    }
}
