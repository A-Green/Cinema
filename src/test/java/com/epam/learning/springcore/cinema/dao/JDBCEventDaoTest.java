package com.epam.learning.springcore.cinema.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;
import com.epam.learning.springcore.cinema.model.Rating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class JDBCEventDaoTest extends ContextSupportTest {
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private AuditoriumDao auditoriumDao;

	private static boolean setupDone = false;
	private static Event event;
	private static final String EVENT_NAME = "Terminator2";
	
	@Before
	public void init() {
		if (!setupDone) {
			event = context.getBean(Movie.class);
			event.setRating(Rating.LOW);
			event.setName(EVENT_NAME);
			event.setBaseTicketPrice(100);
			setupDone = true;
		}
	}
	
	@Test
	public void saveTest(){
		event = eventDao.save(event);
		assertNotNull(event.getId());
	}
	
	@Test
	public void getByNameTest() {
		Event testEvent = eventDao.getByName(EVENT_NAME).get(0);
		assertEquals(event, testEvent);
	}
	
	@Test
	public void getAllEvents() {
		Event event2 = context.getBean(Movie.class);
		eventDao.save(event2);
		
		List<Event> allEvents = eventDao.getAllEvents();
		assertTrue(allEvents.contains(event));
		assertTrue(allEvents.contains(event2));
		
		eventDao.remove(event2.getId());
	}
	
	
	@Test
	public void assignAuditoriumTest() {
		Date date = new Date();
		//Assuming that aud1 exists (populated from populate-db script)
		Auditorium aud1 = auditoriumDao.getByName("aud1");
		assertNotNull(aud1);
		eventDao.assignAuditorium(event, aud1, date);
		
		List<Event> assignedEvents = eventDao.getEventsForDate(date);
		System.out.println(assignedEvents);
		//TODO figure out how to select by date. probably millis does not stored
	}
	
	@Test
	public void removeTest() {
		eventDao.remove(event.getId());
		assertNull(eventDao.getById(event.getId()));
	}
}
