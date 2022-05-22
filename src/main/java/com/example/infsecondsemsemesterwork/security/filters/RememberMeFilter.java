package com.example.infsecondsemsemesterwork.security.filters;

import com.example.infsecondsemsemesterwork.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.infsecondsemsemesterwork.repositories.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
@Slf4j
public class RememberMeFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();

		HttpSession session = request.getSession();

		if (session.getAttribute("user") != null) {
			filterChain.doFilter(request, response);
			return;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("remember-me")) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				session.setAttribute("user", UserDto.from(userRepository.findByEmail(auth.getName()).get()));
			}
		}
		filterChain.doFilter(request, response);
	}
}
