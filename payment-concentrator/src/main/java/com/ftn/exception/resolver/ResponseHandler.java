package com.ftn.exception.resolver;

import org.springframework.http.HttpStatus;

import com.ftn.exception.BadRequestException;
import com.ftn.exception.ForbiddenException;
import com.ftn.exception.NotFoundException;

public class ResponseHandler {
	public static void checkResponseStatus(HttpStatus status) {
		if(status.equals(HttpStatus.BAD_REQUEST)) {
			throw new BadRequestException();
		}else if(status.equals(HttpStatus.NOT_FOUND)) {
			throw new NotFoundException();
		}else if (status.equals(HttpStatus.FORBIDDEN)) {
			throw new ForbiddenException();
		}
	}
}
