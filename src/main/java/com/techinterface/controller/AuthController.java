package com.techinterface.controller;

import com.techinterface.security.JwtTokenUtil;
import com.techinterface.service.UserService;
import com.techinterface.utils.JwtRequest;
import com.techinterface.utils.JwtResponse;
import com.techinterface.model.request.SignUpDto;
import com.techinterface.repository.UserRepository;
import com.techinterface.security.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService userDetailsService;

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserService userService;

    @Value("${message.email}")
    private String emailIsExist;

    @PostMapping("/register")
    public Object signup(@RequestBody SignUpDto dto) {
        try {

            if (userService.checkExistByEmail(dto.getEmail())) {
                return new ResponseEntity<Object>(emailIsExist, HttpStatus.BAD_REQUEST);
            }

            userService.signUp(dto);
            return new ResponseEntity<Object>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
