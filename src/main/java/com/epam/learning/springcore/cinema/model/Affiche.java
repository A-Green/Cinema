package com.epam.learning.springcore.cinema.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Affiche {
	//Affiche. <eventid, <auditoriumid, dates>>
	private Map<Integer, Map<String, List<Date>>> affiche = new HashMap<Integer, Map<String,List<Date>>>();
	
	public Affiche() {
		
	}
	public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
		Map<String, List<Date>> row = affiche.get(event.getId());
		if (row == null) {
			row = new HashMap<String, List<Date>>();
		}
		
		List<Date> dates = row.get(auditorium.getId());
		
		if (dates == null){
			dates = new ArrayList<Date>();
		}
		
		dates.add(date);
		row.put(auditorium.getId(), dates);
		affiche.put(event.getId(), row);
	}
	
	public List<String> getAssignedAuditoriums(Event event, Date date){
		List<String> auditories = new ArrayList<String>();
		Map<String, List<Date>> assignement = affiche.get(event.getId());
		if (assignement == null) {
			return null;
		}
		
		for (String auditory: assignement.keySet()) {
			if (assignement.get(auditory).contains(date)) {
				auditories.add(auditory);
			}
		}
		return auditories;
	}
}
