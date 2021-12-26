package com.softmed.maalem.exception;

import com.softmed.maalem.presentation.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler
 {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> badRequest(HttpServletRequest req, Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ApiResponse(false,exception.getMessage()));
    }

     @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
     @ExceptionHandler({Exception.class})
     public ResponseEntity<Object> AllExceptions(HttpServletRequest req, Exception exception) {
         log.error(exception.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                 body(new ApiResponse(false,exception.getMessage()));
     }

}
