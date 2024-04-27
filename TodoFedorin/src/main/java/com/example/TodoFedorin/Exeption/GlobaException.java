package com.example.TodoFedorin.Exeption;

import com.example.TodoFedorin.DTO.Responses.BaseSuccessResponse;
import com.example.TodoFedorin.Tools.ErrorCodes;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobaException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseSuccessResponse> baseException(CustomException ex) {
        return badRequest(new BaseSuccessResponse(ex.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseSuccessResponse> notValidException(MethodArgumentNotValidException ex) {
        Set<Integer> codes = ex.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .map(ErrorCodes::findErrorCodeByMessage)
                .map(ErrorCodes::getStatusCode)
                .collect(Collectors.toSet());

        return badRequest(new BaseSuccessResponse(codes));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseSuccessResponse> constraintViolationException(ConstraintViolationException ex) {
        Set<Integer> codes = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessageTemplate)
                .map(ErrorCodes::findErrorCodeByMessage)
                .map(ErrorCodes::getStatusCode)
                .collect(Collectors.toSet());

        return badRequest(new BaseSuccessResponse(codes));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseSuccessResponse> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return badRequest(new BaseSuccessResponse(ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getStatusCode()));
    }

    private <T> ResponseEntity<T> badRequest(T response) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
