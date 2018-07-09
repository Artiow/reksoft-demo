package ru.reksoft.demo.service;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.dto.UserDTO;
import ru.reksoft.demo.dto.security.TokenDTO;
import ru.reksoft.demo.mapper.UserMapper;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.repository.UserRoleRepository;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.security.SecurityService;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class UserService {

    @Value("${jwt.token-type}")
    private String TOKEN_TYPE;

    private MessagesConfig messages;

    private BCryptPasswordEncoder passwordEncoder;

    private SecurityService securityService;

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    private UserMapper userMapper;

    @Autowired
    public void setMessages(MessagesConfig messages) {
        this.messages = messages;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    /**
     * Login user and returns TokenDTO.
     *
     * @param login    - user's login
     * @param password - users's password
     * @return token info
     * @throws UsernameNotFoundException - if user not found
     * @throws JwtException              - if could not parse jwt
     */
    @Transactional(readOnly = true)
    public TokenDTO login(@NotNull String login, @NotNull String password) throws UsernameNotFoundException, JwtException {
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

        return new TokenDTO()
                .setUser(userMapper.toShortDTO(user))
                .setTokenType(TOKEN_TYPE)
                .setAccessToken(
                        securityService.login(
                                User.builder()
                                        .username(user.getLogin())
                                        .password(user.getPassword())
                                        .roles(user.getRole().getCode().toUpperCase())
                                        .build()
                        )
                );
    }

    /**
     * Register new user and returns his id.
     *
     * @param userDTO - user data
     * @return user id
     * @throws ResourceCannotCreateException - if user could not create
     */
    @Transactional
    public Integer register(@NotNull UserDTO userDTO) throws ResourceCannotCreateException {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Integer roleId = userDTO.getRole().getId();
        String login = userDTO.getLogin();

        if (!userRoleRepository.existsById(roleId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.UserRole.notExistById.message", roleId
            ));
        } else if (userRepository.existsByLogin(login)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.User.alreadyExistByLogin.message", login
            ));
        }

        return userRepository.save(userMapper.toEntity(userDTO)).getId();
    }
}
