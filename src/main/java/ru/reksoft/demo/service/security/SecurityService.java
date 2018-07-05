package ru.reksoft.demo.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    // todo: return user's token
    public Optional<String> login(String username, String password) {
        return Optional.empty();
    }

    // todo: return my impl of UserDetails found by token
    public Optional<UserDetails> authentication(String token) {
        return Optional.empty();
    }
}
