package com.dskimina.exceptions;

/**
 * Created by Daniel on 17.08.2017.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
