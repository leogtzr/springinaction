package com.taco.service;

import com.taco.data.UserRepository;
import com.taco.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final User user = userRepository.findByUserName(userName);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + userName + "' not found");
    }
}
