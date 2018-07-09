package ru.reksoft.demo.controller.api;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.UserDTO;
import ru.reksoft.demo.dto.security.TokenDTO;
import ru.reksoft.demo.dto.shortcut.UserShortDTO;
import ru.reksoft.demo.service.UserService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("${api-path.user}")
public class UserController {

    @Value("${api-path.user}")
    private String apiPath;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * Returns user data by sent id.
     *
     * @param id - user id
     * @return user dto
     * @throws ResourceNotFoundException - if user not found
     */
    @GetMapping("/{id}")
    public UserShortDTO get(@PathVariable Integer id) throws ResourceNotFoundException {
        return userService.get(id);
    }

    /**
     * Returns TokenDTO by logged user.
     *
     * @param login    - user's login
     * @param password - user's password
     * @return token data
     */
    @GetMapping("/login")
    public TokenDTO login(@RequestParam String login, @RequestParam String password) throws UsernameNotFoundException, JwtException {
        return userService.login(login, password);
    }

    /**
     * Register new user.
     *
     * @param userDTO  - new user data
     * @param response - http response
     */
    @PostMapping("/register")
    public void register(@RequestBody @Validated(UserDTO.FieldCheck.class) UserDTO userDTO, HttpServletResponse response)
            throws ResourceCannotCreateException {
        response.setHeader(HttpHeaders.LOCATION, ResourceLocationBuilder.build(apiPath, userService.register(userDTO)));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
