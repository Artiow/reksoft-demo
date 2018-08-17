package ru.reksoft.demo.service;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.messages.MessageContainer;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.dto.UserDTO;
import ru.reksoft.demo.dto.security.TokenDTO;
import ru.reksoft.demo.dto.shortcut.UserShortDTO;
import ru.reksoft.demo.mapper.UserMapper;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.repository.UserRoleRepository;
import ru.reksoft.demo.service.generic.*;
import ru.reksoft.demo.service.security.SecurityService;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUser;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class UserService extends AbstractSecurityService {

    private BCryptPasswordEncoder passwordEncoder;

    private SecurityService securityService;

    private UserMapper userMapper;

    @Autowired
    public void setMessages(MessageContainer messages) {
        super.setMessages(messages);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        super.setUserRepository(userRepository);
    }

    @Autowired
    protected void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        super.setUserRoleRepository(userRoleRepository);
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
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    /**
     * Returns user data by user id.
     *
     * @param id - user id
     * @return user's data
     * @throws ResourceNotFoundException - if user with sent id not found
     */
    @Transactional(readOnly = true)
    public UserShortDTO get(@NotNull Integer id) throws ResourceNotFoundException, AuthorizationRequiredException, ForbiddenAccessException {
        if (!id.equals(getCurrentUserId())) {
            throw new ForbiddenAccessException(getMessages().get("reksoft.demo.User.forbiddenAccessAttempt.message"));
        } else {
            try {
                return userMapper.toShortDTO(getUserRepository().getOne(id));
            } catch (EntityNotFoundException e) {
                throw new ResourceNotFoundException(getMessages().getAndFormat("reksoft.demo.User.notExistById.message", id), e);
            }
        }
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
            user = getUserRepository().findByLogin(login);
            if (user == null) {
                throw new EntityNotFoundException(getMessages().get("reksoft.demo.auth.service.repository.message"));
            } else if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException(getMessages().get("reksoft.demo.auth.service.encoder.message"));
            }

        } catch (EntityNotFoundException | BadCredentialsException e) {
            throw new UsernameNotFoundException(getMessages().get("reksoft.demo.auth.service.loginError.message"), e);
        }

        return new TokenDTO()
                .setUser(userMapper.toAuthDTO(user))
                .setTokenType(securityService.getTokenType())
                .setAccessToken(
                        securityService.login(
                                new IdentifiedUser(
                                        user.getId(),
                                        User.builder()
                                                .username(user.getLogin())
                                                .password(user.getPassword())
                                                .roles(user.getRole().getCode().toUpperCase())
                                                .build()
                                )
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
        String login = userDTO.getLogin();
        if (getUserRepository().existsByLogin(login)) {
            throw new ResourceCannotCreateException(getMessages().getAndFormat(
                    "reksoft.demo.User.alreadyExistByLogin.message", login
            ));
        }

        UserEntity user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(getUserRoleRepository().findByCode("user"));
        return getUserRepository().save(user).getId();
    }
}
