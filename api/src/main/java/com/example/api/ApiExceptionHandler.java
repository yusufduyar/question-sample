package com.example.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleAuthenticationFailed(AuthenticationFailedException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        try {
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<Object>("",new HttpHeaders(),HttpStatus.I_AM_A_TEAPOT);
        }
    }
}

