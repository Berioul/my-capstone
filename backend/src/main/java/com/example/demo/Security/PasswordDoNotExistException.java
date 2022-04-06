package com.example.demo.Security;

public class PasswordDoNotExistException extends IllegalStateException {

    PasswordDoNotExistException(){
        super("password does not match");
    }
}
