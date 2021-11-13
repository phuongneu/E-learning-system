package com.techinterface.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.techinterface.entity.User;
import com.techinterface.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.techinterface.model.dto.UserDetailDto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Value("${message.username}")
    private String emailIsNotExist;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // // lấy ra user theo email
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException(emailIsNotExist);

        // lấy ra role name của role trong user
        String roleName = user.getRole().getName();

        // tạo list role
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(roleName));

        return new UserDetailDto(email, user.getPassword(), authorities);

    }
}