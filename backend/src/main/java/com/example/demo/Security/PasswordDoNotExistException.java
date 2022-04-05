package com.example.demo.Security;

public class PasswordDoNotExistException extends IllegalStateException {

    PasswordDoNotExistException(){
        super("password do not match");
    }
}
