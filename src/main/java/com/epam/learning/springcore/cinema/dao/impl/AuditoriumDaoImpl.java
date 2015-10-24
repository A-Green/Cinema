package com.epam.learning.springcore.cinema.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epam.learning.springcore.cinema.dao.AuditoriumDao;
import com.epam.learning.springcore.cinema.model.Auditorium;

public class AuditoriumDaoImpl implements AuditoriumDao {

	private Map<String, Auditorium> auditoriums;
	
	@Override
	public List<Auditorium> getAuditoriums() {
		List<Auditorium> auditoriumsLst = new ArrayList<>();
		for (String name: auditoriums.keySet()) {
			auditoriumsLst.add(auditoriums.get(name));
		}
		return auditoriumsLst;
	}

	@Override
	public int getSeatsNumber(String auditName) {
		Auditorium auditorium = auditoriums.get(auditName);
		if (auditorium != null) {
			return auditorium.getSeatsNumber();
		}
		//TODO handle this smarter later
		return -1;
	}

	@Override
	public List<Integer> getVipSeats(String auditName) {
		Auditorium auditorium = auditoriums.get(auditName);
		if (auditorium != null) {
			return auditorium.getVipSeats();
		}
		//TODO handle this smarter later
		return null;
	}
}
