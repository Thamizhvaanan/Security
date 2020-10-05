package io.javabrains.springsecurityjpa.service;

import io.javabrains.springsecurityjpa.repository.UserRepository;
import io.javabrains.springsecurityjpa.models.MyUserDetails;

import io.javabrains.springsecurityjpa.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        if(user == null){
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
