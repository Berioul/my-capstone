package com.example.demo.Security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreationData userCreationData) {
        LOGGER.info("user with username {} should be created", userCreationData.getUsername());
        try {
            userService.createUser(userCreationData);
            LOGGER.info("user with username {} was created", userCreationData.getUsername());
            return ResponseEntity.status(201).body("user was created");
        } catch (UserAlreadyExistException e) {
            LOGGER.info("user with username {} already exists", userCreationData.getUsername());
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (PasswordDoNotExistException e) {
            LOGGER.info("user with username {} did send paswords that did not match", userCreationData.getUsername());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginData loginData) {
        LOGGER.info("user with username {} tries to log in", loginData.getUsername());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
            Token token = new Token();
            token.setToken(jwtUtils.createToken(new HashMap<>(), loginData.getUsername()));
            return token;
        } catch (Exception e) {
            LOGGER.info("user " + loginData.getUsername() + " could not be authenticated", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
