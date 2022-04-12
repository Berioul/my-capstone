package de.sahbani.backend.Security;

public class UserAlreadyExistException extends IllegalStateException{

    UserAlreadyExistException(){
        super("user already exists");
    }
}
