package com.mislavmatijevic.nutritym.backend.exceptions;

public class PhotoNotFoundException extends RuntimeException
{
    public PhotoNotFoundException(long userId)
    {
        super("There is no photo with an id " + userId);
    }
}
