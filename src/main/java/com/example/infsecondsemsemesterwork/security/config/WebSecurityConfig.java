package com.example.infsecondsemsemesterwork.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.infsecondsemsemesterwork.security.details.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SavedRequestAwareAuthenticationSuccessHandler awareAuthenticationSuccessHandler;

	@Autowired
	private OncePerRequestFilter rememberMeFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.addFilterAfter(rememberMeFilter, BasicAuthenticationFilter.class);

		http
				.rememberMe()
				.rememberMeParameter("rememberMe")
				.tokenRepository(tokenRepository())
				.tokenValiditySeconds(60 * 60 * 24 * 365)
				.and()
				.authorizeRequests()
				.antMatchers("/signUp").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/profile").authenticated()
				.antMatchers("/tips").permitAll()
				.antMatchers("/secrets").permitAll()
				.antMatchers("/tips/**").authenticated()
				.antMatchers("/secrets/**").authenticated()
				.and()
				.formLogin()
				.loginPage("/signIn")
				.defaultSuccessUrl("/profile")
				.failureUrl("/signIn?reason=error")
				.usernameParameter("email")
				.passwordParameter("password")
				.successHandler(awareAuthenticationSuccessHandler)
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signOut"))
				.logoutSuccessUrl("/signIn?reason=signOut")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and();
	}

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

}
