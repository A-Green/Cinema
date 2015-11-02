package com.epam.learning.springcore.cinema.model;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Schedule extends Entity<String> {

	private List<Date> eventDate;
	
	public Schedule() {
	}

	public List<Date> getEventDate() {
		return eventDate;
	}

	public void setEventDate(List<Date> eventDate) {
		this.eventDate = eventDate;
	}
}
