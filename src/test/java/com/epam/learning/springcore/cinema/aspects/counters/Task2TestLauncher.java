package com.epam.learning.springcore.cinema.aspects.counters;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class Task2TestLauncher {

	@Test
	public void run() {
		JUnitCore.runClasses(
				BookTicketCounterTest.class, 
				NameAccessCounterTest.class,
				PriceQueryCounterTest.class,
				UserDiscountCounterTest.class);
	}
}
