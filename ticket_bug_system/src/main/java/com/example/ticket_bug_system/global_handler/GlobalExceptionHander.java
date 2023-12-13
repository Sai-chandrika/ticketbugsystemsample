package com.example.ticket_bug_system.global_handler;

import com.example.ticket_bug_system.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 12-10-2023
 */
@RestControllerAdvice
public class GlobalExceptionHander {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleExceptions(Exception exception, WebRequest request) {

        if (exception instanceof RuntimeException) {
          return composeRunTimeException(exception, request);
        } else if (exception instanceof SQLTimeoutException) {
            return composeTimeOutException(exception, request);
        }
        else {
            return composeGenericException(exception, request);
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        List<String> listOfError=new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
            listOfError.add(error.getDefaultMessage());
        }
        return new ResponseEntity<GenericResponse>(composeAppResponseDTO(errors.values().toArray()[errors.values().size()-1].toString(),400,errors), HttpStatus.BAD_REQUEST);

    }




    private ResponseEntity<GenericResponse> composeRunTimeException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(composeAppResponseDTO(exception.getMessage(), 400, request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<GenericResponse> composeTimeOutException(Exception ex, WebRequest request) {
        return new ResponseEntity<GenericResponse>(composeAppResponseDTO(ex.getMessage(), 504, request.getDescription(false)), HttpStatus.GATEWAY_TIMEOUT);
    }

    private ResponseEntity<GenericResponse> composeGenericException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(composeAppResponseDTO(exception.getMessage(), 401, request.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }

    private GenericResponse composeAppResponseDTO(String message, int errorCode, Object payload) {
        return new GenericResponse(errorCode, message, payload);
    }
}
