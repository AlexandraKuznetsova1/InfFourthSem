package com.itis.security.details;

import com.itis.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new CustomUserDetails(
                userRepository.findByLogin(login)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("Could not find user by login in UserDetailsService")));
    }
}
