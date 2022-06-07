package com.itis.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.respositories.BlackListRepository;
import com.itis.security.config.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final String secretKey;
    private final BlackListRepository blackListRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(SecurityConfiguration.LOGIN_PAGE)
                || request.getRequestURI().equals(SecurityConfiguration.LOGOUT_REQUEST)) {
            filterChain.doFilter(request, response);
            return;
        }
        String tokenHeader = request.getHeader(TokenAuthenticationFilter.AUTHORIZATION_HEADER_NAME);
        if (tokenHeader == null) {
            log.info("Токена нет");
            filterChain.doFilter(request, response);
            return;
        }
        if (!tokenHeader.startsWith(TokenAuthenticationFilter.AUTH_TOKEN_PREFIX)) {
            log.info("Токен не того формата");
            sendForbidden(response);
            return;
        }

        String token = tokenHeader.substring(TokenAuthenticationFilter.AUTH_TOKEN_PREFIX.length());
        if(blackListRepository.exists(token)){
            log.warn("Токен в черном списке");
            filterChain.doFilter(request, response);
            return;
        }
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                    .build().verify(token);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(token, null,
                            Collections.singletonList(new SimpleGrantedAuthority(decodedJWT.getClaim("role").asString())));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            String login = JWT.decode(token).getClaim("login").asString();
            log.info("Напишите токен для пользователя" + login);
            sendForbidden(response);
        }
    }

    private void sendForbidden(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap("error", "пользователь не найден по токену"));
    }

}

