package com.itis.security.filters;

import com.itis.respositories.BlackListRepository;
import com.itis.security.config.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class TokenLogoutFilter extends OncePerRequestFilter {

    private final BlackListRepository blackListRepository;
    private final RequestMatcher logoutMatcher =
            new AntPathRequestMatcher(SecurityConfiguration.LOGOUT_REQUEST, HttpMethod.GET.name());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        if (!logoutMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String tokenHeader = request.getHeader(TokenAuthenticationFilter.AUTHORIZATION_HEADER_NAME);
        if (tokenHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!tokenHeader.startsWith(TokenAuthenticationFilter.AUTH_TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenHeader.substring(TokenAuthenticationFilter.AUTH_TOKEN_PREFIX.length());
        blackListRepository.save(token);
        SecurityContextHolder.clearContext();
    }

}
