package com.example.infsecondsemsemesterwork.security.details;

import com.example.infsecondsemsemesterwork.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new CustomUserDetails(
				userRepository.findByEmail(email)
						.orElseThrow(
								() -> new UsernameNotFoundException("User not found")));
	}
}
