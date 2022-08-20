package com.mislavmatijevic.nutritym.backend.exceptions;

public class UserAlreadyExistsException extends RuntimeException
{
    public UserAlreadyExistsException()
    {
        super("There is already a registered user with the provided email");
    }
}
