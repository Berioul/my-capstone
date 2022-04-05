package com.example.demo.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

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
        userRepo.findByUserName(userCreationData.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException();
                });
        return Objects.equals(userCreationData.getPassword(),userCreationData.getPasswordAgain());
        }
}
