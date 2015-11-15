package com.epam.learning.springcore.cinema.dao.impl.mapbased;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.learning.springcore.cinema.dao.AuditoriumDao;
import com.epam.learning.springcore.cinema.model.Auditorium;

@Repository
public class AuditoriumDaoImpl implements AuditoriumDao {

	private static Map<String, Auditorium> auditoriumsMap;
	
	public  AuditoriumDaoImpl() {
	}
	
	public void setAuditoriumsMap(Map<String, Auditorium> auditoriums){
		auditoriumsMap = auditoriums;
	}
	
	@Override
	public List<Auditorium> getAuditoriums() {
		List<Auditorium> auditoriumsLst = new ArrayList<>();
		for (String name: auditoriumsMap.keySet()) {
			auditoriumsLst.add(auditoriumsMap.get(name));
		}
		return auditoriumsLst;
	}

	@Override
	public int getSeatsNumber(String auditName) {
		Auditorium auditorium = auditoriumsMap.get(auditName);
		if (auditorium != null) {
			return auditorium.getSeatsNumber();
		}
		//TODO handle this smarter later
		return -1;
	}

	@Override
	public List<Integer> getVipSeats(String auditName) {
		Auditorium auditorium = auditoriumsMap.get(auditName);
		if (auditorium != null) {
			return auditorium.getVipSeats();
		}
		//TODO handle this smarter later
		return null;
	}

	@Override
	public Auditorium getByName(String name) {
		return auditoriumsMap.get(name);
	}

	@Override
	public List<Auditorium> getAssignedAuditoriums(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
}
