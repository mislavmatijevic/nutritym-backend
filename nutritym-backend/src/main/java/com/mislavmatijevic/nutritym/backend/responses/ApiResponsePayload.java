package com.mislavmatijevic.nutritym.backend.responses;

public class ApiResponsePayload<T> extends ApiResponse
{
    private final T payload;

    public ApiResponsePayload(final boolean success, final String message, final T payload)
    {
        super(success, message);
        this.payload = payload;
    }

    public T getPayload()
    {
        return payload;
    }
}
