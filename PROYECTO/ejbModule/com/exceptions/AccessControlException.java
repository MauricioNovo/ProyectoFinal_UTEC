package com.exceptions;

public class AccessControlException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	Throwable unrollException(Throwable exception, Class<? extends Throwable> expected){

	    while(exception != null && exception != exception.getCause()){
	        if(expected.isInstance(exception)){
	          return exception;
	        }
	        exception = exception.getCause();
	    }
	    return null;
	}
}
