package ru.reksoft.demo.service.generic;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.reksoft.demo.config.messages.MessageContainer;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.repository.UserRoleRepository;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

public class AbstractSecurityService {

    private MessageContainer messages;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    protected MessageContainer getMessages() {
        return messages;
    }

    protected void setMessages(MessageContainer messages) {
        this.messages = messages;
    }

    protected UserRepository getUserRepository() {
        return userRepository;
    }

    protected void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected UserRoleRepository getUserRoleRepository() {
        return userRoleRepository;
    }

    protected void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    /**
     * Returns authenticated user id.
     *
     * @return user id
     * @throws AuthorizationRequiredException - if user is anonymous
     */
    protected Integer getCurrentUserId() throws AuthorizationRequiredException {
        try {
            return ((IdentifiedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        } catch (ClassCastException | NullPointerException e) {
            throw new AuthorizationRequiredException(messages.get("reksoft.demo.auth.filter.credentialsNotFound.message"), e);
        }
    }

    /**
     * Returns authenticated user.
     *
     * @return user entity
     * @throws AuthorizationRequiredException - if user is anonymous
     */
    protected UserEntity getCurrentUserEntity() throws AuthorizationRequiredException {
        return userRepository.getOne(getCurrentUserId());
    }
}
