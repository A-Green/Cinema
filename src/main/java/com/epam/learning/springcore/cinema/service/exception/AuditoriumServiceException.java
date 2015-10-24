package com.epam.learning.springcore.cinema.service.exception;

public class AuditoriumServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public AuditoriumServiceException(String errorMessage) {
		super(errorMessage);
	}
}
