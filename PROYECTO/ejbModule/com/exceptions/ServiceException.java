package com.exceptions;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ServiceException (String mensaje){
		super(mensaje);
	}

}
