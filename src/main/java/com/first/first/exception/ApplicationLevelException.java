package com.first.first.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationLevelException {
   
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(ResourceNotFoundException e, WebRequest request){
    	ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e, WebRequest request){
    	ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
    	return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
    }
	
    @ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAnyException(Exception e, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
	}
    

}

