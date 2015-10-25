package com.epam.learning.springcore.cinema.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Rating;
import com.epam.learning.springcore.cinema.service.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class EventServiceTest {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@Test
	public void test() throws ServiceException {
		Event event = eventService.create("testEvent", 100, Rating.HIGH);
		assertNotNull(event.getId());
		
		List<Event> events = eventService.getAll();
		assertEquals(events.size(), 1, 0);
		
		events = eventService.getByName("testEvent");
		assertEquals(events.size(), 1, 0);
		for (Event e: events){
			assertEquals(e.getName(), "testEvent");
		}
		
		Auditorium auditorium = auditoriumService.getAuditoriums().get(0);
		Date date = new Date(System.currentTimeMillis());
		eventService.assignAuditorium(event, auditorium, date);
		
		List<String> auditNames = eventService.getAffiche().getAssignedAuditoriums(event, date);
		assertNotNull(auditNames);
		assertEquals(auditNames.size(),1,0);
		assertTrue(auditNames.get(0) == auditorium.getName());
	}
}
