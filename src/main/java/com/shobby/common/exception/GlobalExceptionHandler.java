package com.shobby.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException exception,
//                                                                    HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(apiError);
//    }
//
//    @ExceptionHandler(EnumConstantNotPresentException.class)
//    public ResponseEntity<ApiError> handleEnumConstantNotPresentException(EnumConstantNotPresentException exception,
//                                                                    HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(apiError);
//    }
//
//
//    @ExceptionHandler(PasswordNotMatchedException.class)
//    public ResponseEntity<ApiError> handlePasswordNotMatchedException(PasswordNotMatchedException exception,
//                                                                    HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(apiError);
//    }
//
//    @ExceptionHandler(InvalidRefreshTokenException.class)
//    public ResponseEntity<ApiError> handleInvalidRefreshTokenException(InvalidRefreshTokenException exception,
//                                                                       HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(apiError);
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException exception,
//                                                                       HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(),
//                HttpStatus.NOT_FOUND.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(apiError);
//    }
//
//    @ExceptionHandler(RoleNotFoundException.class)
//    public ResponseEntity<ApiError> handleRoleNotFoundExceptionException(RoleNotFoundException exception,
//                                                                       HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(),
//                HttpStatus.NOT_FOUND.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(apiError);
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException exception,
//                                                                          HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),
//                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(apiError);
//    }
//
//    @ExceptionHandler(DatabaseErrorException.class)
//    public ResponseEntity<ApiError> handleDatabaseErrorException(DatabaseErrorException exception,
//                                                                          HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(apiError);
//    }
//
//    @ExceptionHandler(DisabledException.class)
//    public ResponseEntity<ApiError> handleDisabledException(DisabledException exception,
//                                                                          HttpServletRequest request) {
//        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN.value(),
//                HttpStatus.FORBIDDEN.getReasonPhrase(),
//                exception.getMessage(),
//                request.getRequestURI(),
//                Instant.now());
//        return ResponseEntity
//                .status(HttpStatus.FORBIDDEN)
//                .body(apiError);
//    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> handleCustomException(CustomException exception,
                                                          HttpServletRequest request) {
        ApiError apiError = new ApiError(exception.getErrorCode(),
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now());
        return ResponseEntity
                .status(exception.getErrorCode())
                .body(apiError);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception,
                                                    HttpServletRequest request) {
        log.error("Unhandled exception at {}: {}", request.getRequestURI(), exception.getMessage(), exception);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                request.getRequestURI(),
                Instant.now());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }


}
