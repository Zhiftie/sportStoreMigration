package com.sportstore.authservice;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.sportstore.authservice.user.User;
import com.sportstore.authservice.user.UserRepository;

import lombok.RequiredArgsConstructor;

/*
@Service
*/
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getCredentials() == null) {
            return null;
        }
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByName(name);
        if(authentication.getCredentials().toString().equals(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(name,
                    password);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
