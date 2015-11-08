package com.epam.learning.springcore.cinema.aspects.counters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.aspects.counters.PriceQueryCounter;
import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class PriceQueryCounterTest {

	@Autowired
	private PriceQueryCounter priceQueryCounter;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	private Event event;
	
	@Before
	public void init(){
		assertNotNull(priceQueryCounter);
		assertNotNull(applicationContext);
		event = applicationContext.getBean(Movie.class);
		event.setId(1);
		event.setName("Terminator");
		event.setBaseTicketPrice(100);
	}
	
	@Test
	public void priceCountTest(){
		int countExpected = 10;
		for (int i = 0; i < countExpected; i++){
			event.getBaseTicketPrice();
		}
		assertTrue(priceQueryCounter.getCount(1) == countExpected);
	}
}
