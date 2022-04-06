package com.example.demo.Security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserCreationData userCreationData) {
        if (userCreationDataIsValid(userCreationData)) {
               User user = new User(null, userCreationData.getUsername(), passwordEncoder.encode(userCreationData.getPassword()));
               return userRepo.save(user);
           }
            throw new PasswordDoNotExistException();

        }
        private boolean userCreationDataIsValid (UserCreationData userCreationData){
        userRepo.findByUsername(userCreationData.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException();
                });
        return Objects.equals(userCreationData.getPassword(),userCreationData.getPasswordAgain());
        }
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
