package io.zelun.pokeapiapp.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeignExceptionHandler {
    @ExceptionHandler(value = {FeignException.class})
    public ResponseEntity<Object> handleFeignException(FeignException e){
        //Todo currently only returning the message to the client, to be enhanced with customised APIExcpetionResponse
       return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.status()));
    }
}
