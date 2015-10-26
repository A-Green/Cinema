package com.epam.learning.springcore.cinema.aspects.event;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class EventCountersTest {

	@Autowired
	private NameAccessCounter nameAccessCounter;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void nameAccessTest(){
		assertNotNull(nameAccessCounter);
		assertNotNull(applicationContext);
		
		Movie event = applicationContext.getBean(Movie.class);
		event.setId(1);
		event.setName("Terminator");
		event.getName();
		System.out.println(nameAccessCounter.getNameAccesCounter().get(1));
		assertTrue(nameAccessCounter.getNameAccesCounter().get(1) == 1);
	}
}
