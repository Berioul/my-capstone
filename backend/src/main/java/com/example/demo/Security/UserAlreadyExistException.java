package com.example.demo.Security;

public class UserAlreadyExistException extends IllegalStateException{

    UserAlreadyExistException(){
        super("user already exists");
    }
}
