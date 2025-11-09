package com.mohit.lld.expenserulesystem.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldValidationError> fieldErrors = new ArrayList<>();
        
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.add(new FieldValidationError(
                error.getField(),
                error.getDefaultMessage(),
                error.getRejectedValue()
            ));
        }
        
        ErrorResponse response = new ErrorResponse(
            "Validation Failed",
            ex.getBindingResult().getObjectName(),
            fieldErrors
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<SimpleErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex) {
        String message = "Invalid JSON format";
        
        if (ex.getCause() instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException upex = (UnrecognizedPropertyException) ex.getCause();
            message = String.format("Field '%s' does not exist in %s. Check that ruleType matches ruleData structure.",
                upex.getPropertyName(),
                upex.getReferringClass().getSimpleName()
            );
        }
        
        SimpleErrorResponse response = new SimpleErrorResponse(
            "Invalid Data Structure",
            message
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SimpleErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        SimpleErrorResponse response = new SimpleErrorResponse(
            "Invalid Request",
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<SimpleErrorResponse> handleNullPointer(NullPointerException ex) {
        SimpleErrorResponse response = new SimpleErrorResponse(
            "Data Error",
            "Required data is missing. Check that ruleType matches ruleData structure."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SimpleErrorResponse> handleGenericException(Exception ex) {
        SimpleErrorResponse response = new SimpleErrorResponse(
            "Server Error",
            "An unexpected error occurred. Please check your request."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static class SimpleErrorResponse {
        public String error;
        public String message;

        public SimpleErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }
    }

    public static class ErrorResponse {
        public String error;
        public String objectName;
        public List<FieldValidationError> errors;

        public ErrorResponse(String error, String objectName, List<FieldValidationError> errors) {
            this.error = error;
            this.objectName = objectName;
            this.errors = errors;
        }
    }

    public static class FieldValidationError {
        public String field;
        public String message;
        public Object rejectedValue;

        public FieldValidationError(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }
    }
}


