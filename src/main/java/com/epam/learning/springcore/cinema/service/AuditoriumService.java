package com.epam.learning.springcore.cinema.service;

import java.util.List;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.service.exception.AuditoriumServiceException;

public interface AuditoriumService {
	List<Auditorium> getAuditoriums() throws AuditoriumServiceException;
	int getSeatsNumber(String auditName) throws AuditoriumServiceException;
	List<Integer> getVipSeats(String auditName) throws AuditoriumServiceException;
	Auditorium getByName(String name) throws AuditoriumServiceException;
}
