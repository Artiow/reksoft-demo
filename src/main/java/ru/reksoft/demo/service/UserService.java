package ru.reksoft.demo.service;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.service.security.SecurityService;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private MessagesConfig messages;

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private SecurityService securityService;

    @Autowired
    public void setMessages(MessagesConfig messages) {
        this.messages = messages;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Transactional(readOnly = true)
    public String login(String login, String password) throws UsernameNotFoundException, JwtException {
        UserEntity user;

        try {
            user = userRepository.findByLogin(login);
            if (user == null) {
                throw new EntityNotFoundException(messages.get("reksoft.demo.auth.service.repository.message"));
            } else if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException(messages.get("reksoft.demo.auth.service.encoder.message"));
            }

        } catch (EntityNotFoundException | BadCredentialsException e) {
            throw new UsernameNotFoundException(messages.get("reksoft.demo.auth.service.loginError.message"), e);
        }

        return securityService.login(
                User.builder()
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .roles(user.getRole().getCode().toUpperCase())
                        .build());
    }
}
