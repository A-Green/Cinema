package com.epam.learning.springcore.cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.dao.AuditoriumDao;
import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.service.AuditoriumService;
import com.epam.learning.springcore.cinema.service.exception.AuditoriumServiceException;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	//@Qualifier("preparedAuditouriumDao")
	private AuditoriumDao auditoriumDao;
	
	@Override
	public List<Auditorium> getAuditoriums() throws AuditoriumServiceException {
		return auditoriumDao.getAuditoriums();
	}

	@Override
	public int getSeatsNumber(String auditName) throws AuditoriumServiceException {
		int seatsNumber = auditoriumDao.getSeatsNumber(auditName);
		if (seatsNumber < 0) {
			throw new AuditoriumServiceException("Auditorium not foud " + auditName);
		}
		return seatsNumber;
	}

	@Override
	public List<Integer> getVipSeats(String auditName) throws AuditoriumServiceException {
		List<Integer> seats = auditoriumDao.getVipSeats(auditName);
		if (seats == null) {
			throw new AuditoriumServiceException("Auditorium not foud " + auditName);
		}
		return seats;
	}

	@Override
	public Auditorium getByName(String name) throws AuditoriumServiceException {
		return auditoriumDao.getByName(name);
	}

}
