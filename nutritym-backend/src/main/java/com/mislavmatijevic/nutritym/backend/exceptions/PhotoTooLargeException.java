package com.mislavmatijevic.nutritym.backend.exceptions;

public class PhotoTooLargeException extends RuntimeException
{
    public PhotoTooLargeException(long receivedSize)
    {
        super("Uploaded image is " + (receivedSize / 1024.0) + " KB large. Image size cannot be more than 200 KB in size!");
    }
}