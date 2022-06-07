package com.itis.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.dtos.SignInDto;
import com.itis.models.User;
import com.itis.security.details.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String AUTH_TOKEN_PREFIX = "Bearer:";
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final String secretKey;
    private final ObjectMapper objectMapper;

    public TokenAuthenticationFilter(String secretKey, ObjectMapper objectMapper, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.secretKey = secretKey;
        this.objectMapper = objectMapper;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            SignInDto signInDto = objectMapper.readValue(request.getReader(), SignInDto.class);
            log.info("Attempt to authenticate for login: " + signInDto.getLogin());
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(signInDto.getLogin(), signInDto.getPassword());

            return super.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
                log.info("Could read SignIn properties", e);
                throw new IllegalArgumentException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("login", user.getLogin())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(Date.valueOf(LocalDate.now().plus(30, ChronoUnit.MINUTES)))
                .sign(Algorithm.HMAC256(secretKey));
        response.setHeader(AUTHORIZATION_HEADER_NAME, AUTH_TOKEN_PREFIX + token);
    }
}
