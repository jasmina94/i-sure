package com.ftn.exception.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ftn.exception.BadRequestException;
import com.ftn.exception.ForbiddenException;
import com.ftn.exception.NotFoundException;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by Jasmina on 16/11/2017.
 */
public class ExceptionResolver {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequestException(HttpServletRequest request, BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundException(HttpServletRequest request, NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity forbiddenException(HttpServletRequest request, ForbiddenException exception) {
        return new ResponseEntity<>("{\"message\": \"" + exception.getMessage() + "\"}", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity errorException(HttpServletRequest request, HttpClientErrorException exception) {
        if (exception.getMessage().equals("403"))
            return new ResponseEntity<>("{\"message\": \"" + exception.getMessage() + "\"}", HttpStatus.FORBIDDEN);
        else
            return new ResponseEntity<>("{\"message\": \"" + exception.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
