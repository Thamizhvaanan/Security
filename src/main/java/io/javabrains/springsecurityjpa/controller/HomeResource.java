package io.javabrains.springsecurityjpa.controller;

import io.javabrains.springsecurityjpa.models.AuthenticationRequest;
import io.javabrains.springsecurityjpa.models.AuthenticationResponse;
import io.javabrains.springsecurityjpa.models.MyUserDetails;
import io.javabrains.springsecurityjpa.models.User;
import io.javabrains.springsecurityjpa.repository.UserRepository;
import io.javabrains.springsecurityjpa.service.MyUserDetailsService;
import io.javabrains.springsecurityjpa.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

//    @GetMapping("/")
//    public UserDetails home()  {
//        User user = userRepository.findByUserName("foo");
//        return new MyUserDetails(user);
//    }

    @GetMapping("/user")
    public String user() {
        return ("Welcome User");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("Welcome Admin");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            final String jwt =jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

}

