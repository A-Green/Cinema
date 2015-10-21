package com.epam.learning.springcore.cinema.service.exception;

public class ServiceException  extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String errorMessage, Throwable e) {
		super(errorMessage, e);
	}

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}

	public ServiceException(Throwable e) {
		super(e);
	}
}
