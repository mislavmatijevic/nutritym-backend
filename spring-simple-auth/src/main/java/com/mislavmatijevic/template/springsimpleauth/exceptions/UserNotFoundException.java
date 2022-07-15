package com.mislavmatijevic.template.springsimpleauth.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException()
    {
        super("There is no such user");
    }
}
