package com.itis.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.respositories.BlackListRepository;
import com.itis.security.filters.AuthorizationFilter;
import com.itis.security.filters.TokenLogoutFilter;
import com.itis.security.filters.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableConfigurationProperties
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_PAGE = "/signIn";
    public static final String LOGOUT_REQUEST = "/logout";
    public static final String USERNAME_PARAMETER = "login";
    public static final String DEFAULT_SUCCESS_URL = "/home/profile";
    public static final String SIGN_UP_REQUEST = "/signUp";


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final BlackListRepository blackListRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;
    private final ObjectMapper objectMapper;


    private TokenAuthenticationFilter tokenAuthenticationFilter;
    private AuthorizationFilter authorizationFilter;
    private TokenLogoutFilter tokenLogoutFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        initAuthenticationFilter();
        initAuthorizationFilter();
        initLogoutFilter();
        tokenAuthenticationFilter.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher(LOGIN_PAGE, HttpMethod.POST.name())
        );

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(tokenAuthenticationFilter);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenLogoutFilter, LogoutFilter.class);


        http
                .authorizeRequests()
                .antMatchers(SIGN_UP_REQUEST).permitAll()
                .antMatchers(LOGOUT_REQUEST+"/**").hasAnyAuthority()
                .antMatchers("/api/**").authenticated();
    }

    private void initLogoutFilter() {
        tokenLogoutFilter = new TokenLogoutFilter(blackListRepository);
    }

    private void initAuthorizationFilter() {
        authorizationFilter = new AuthorizationFilter(objectMapper, secretKey, blackListRepository);
    }

    private void initAuthenticationFilter() throws Exception {
        tokenAuthenticationFilter = new TokenAuthenticationFilter(secretKey, objectMapper, authenticationManagerBean());
    }

}
