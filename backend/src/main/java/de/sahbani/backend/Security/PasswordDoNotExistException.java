package de.sahbani.backend.Security;

public class PasswordDoNotExistException extends IllegalStateException {

    PasswordDoNotExistException(){
        super("password does not match");
    }
}
