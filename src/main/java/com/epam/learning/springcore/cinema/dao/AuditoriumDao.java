package com.epam.learning.springcore.cinema.dao;

import java.util.List;

import com.epam.learning.springcore.cinema.model.Auditorium;

public interface AuditoriumDao {
	List<Auditorium> getAuditoriums();
	int getSeatsNumber(String auditName);
	List<Integer> getVipSeats(String auditName);
}
