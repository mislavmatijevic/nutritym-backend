package com.mislavmatijevic.nutritym.backend.exceptions;

import com.mislavmatijevic.nutritym.backend.responses.ApiResponse;
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

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<ApiResponse> handleUserNotFound(final UserNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, ex.getLocalizedMessage()));
    }

    @ExceptionHandler({PhotoTooLargeException.class})
    protected ResponseEntity<ApiResponse> handlePhotoTooLarge(final PhotoTooLargeException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, ex.getLocalizedMessage()));
    }

    @ExceptionHandler({PhotoNotFoundException.class})
    protected ResponseEntity<ApiResponse> handlePhotoNotFound(final PhotoNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, ex.getLocalizedMessage()));
    }
}
