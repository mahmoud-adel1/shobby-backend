package com.shobby.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiError> handleCustomException(GeneralException exception,
                                                          HttpServletRequest request) {
        ApiError apiError = ApiError
                .builder()
                .statusCode(exception.getHttpStatus().value())
                .errorCode(exception.getErrorCode())
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .build();
        return ResponseEntity
                .status(exception.getHttpStatus().value())
                .body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException exception,
                                                              HttpServletRequest request) {
        List<ValidationError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationErrorMapper::toValidationError)
                .toList();

        ApiError apiError = ApiError
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorCode("VALIDATION_ERROR")
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .details(errors)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception,
                                                    HttpServletRequest request) {
        log.error("Unhandled exception at {}: {}", request.getRequestURI(), exception.getMessage(), exception);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                request.getRequestURI(),
                Instant.now(),
                null);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }


}
