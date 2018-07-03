package ru.reksoft.demo.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    // todo: return user's token
    public Optional<String> login(String username, String password) {
        return Optional.empty();
    }

    // todo: return my impl of UserDetails found by token
    public Optional<UserDetails> authentication(String token) {
        return Optional.empty();
    }

    // todo: logout with token?
    void logout(UserDetails user) {

    }
}
