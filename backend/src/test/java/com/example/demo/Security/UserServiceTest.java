package com.example.demo.Security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


class UserServiceTest {

    @Test
    void shouldCreateUser(){
        UserCreationData userCreationData = new UserCreationData("oualid","123456c","123456c");
        User user = new User(null,"oualid","mysHashPass");
        User savedUser = new User("333","oualid","mysHashPass");


        UserRepo userRepo = Mockito.mock(UserRepo.class);
        Mockito.when(userRepo.save(user)).thenReturn(savedUser);

        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(passwordEncoder.encode("123456c")).thenReturn("mysHashPass");


        UserService userService = new UserService(userRepo,passwordEncoder);
        User actual = userService.createUser(userCreationData);

        Assertions.assertThat(actual).isEqualTo(savedUser);
    }

    @Test
    void shouldNotCreateUser_passwordsDoNotMatch(){

        UserCreationData userCreationData = new UserCreationData("oualid","123456c","123456x");

        UserRepo userRepo = Mockito.mock(UserRepo.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

        UserService userService = new UserService(userRepo,passwordEncoder);


         Assertions.assertThatExceptionOfType(IllegalStateException.class)
                 .isThrownBy(() -> userService.createUser(userCreationData))
                 .withMessage("password does not match");

    }
    @Test
    void shouldNotCreateUser_AlreadyExist(){
        UserCreationData userCreationData = new UserCreationData("oualid","123456c","123456c");
        User existingUser = new User("0014","oualid","hash");

        UserRepo userRepo = Mockito.mock(UserRepo.class);
        Mockito.when( userRepo.findByUsername("oualid")).thenReturn(Optional.of(existingUser));


        UserService userService = new UserService(userRepo, Mockito.mock(PasswordEncoder.class));

        Assertions.assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> userService.createUser(userCreationData))
                .withMessage("user already exists");

    }


}