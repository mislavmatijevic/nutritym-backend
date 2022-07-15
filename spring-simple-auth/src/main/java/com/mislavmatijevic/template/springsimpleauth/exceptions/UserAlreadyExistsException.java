package com.mislavmatijevic.template.springsimpleauth.exceptions;

public class UserAlreadyExistsException extends RuntimeException
{
    public UserAlreadyExistsException()
    {
        super("There is already a registered user with the provided email");
    }
}
