package com.ftn.exception.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ftn.exception.BadRequestException;
import com.ftn.exception.ForbiddenException;
import com.ftn.exception.NotFoundException;

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

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity dataAccessException(HttpServletRequest request, DataAccessException exception) {
        return new ResponseEntity<>("{\"message\": \"" + exception.getMessage() + "\"}", HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity forbiddenException(HttpServletRequest request, ForbiddenException exception) {
        return new ResponseEntity<>("{\"message\": \"" + exception.getMessage() + "\"}", HttpStatus.FORBIDDEN);
    }
}
