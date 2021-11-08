package com.sportstore.authservice.user;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
/*
@Service("customUserDetailsService")
*/
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if(username == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        }
        return new CustomUserDetails(user);
    }
}
