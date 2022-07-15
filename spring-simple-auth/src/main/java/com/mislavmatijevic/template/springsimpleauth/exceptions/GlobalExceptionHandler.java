package com.mislavmatijevic.template.springsimpleauth.exceptions;

import com.mislavmatijevic.template.springsimpleauth.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({UserAlreadyExistsException.class})
    protected ResponseEntity<ApiResponse> handleUserAlreadyExists(final UserAlreadyExistsException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, ex.getLocalizedMessage()));
    }
}
