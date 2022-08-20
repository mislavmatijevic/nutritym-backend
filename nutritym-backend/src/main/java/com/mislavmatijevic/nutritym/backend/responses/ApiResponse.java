package com.mislavmatijevic.nutritym.backend.responses;

public class ApiResponse
{
    private final boolean success;
    private final String message;

    public ApiResponse(final String message)
    {
        this(true, message);
    }

    public ApiResponse(boolean success, final String message)
    {

        this.success = success;
        this.message = message;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }
}
