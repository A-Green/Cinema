package com.epam.learning.springcore.cinema.dao;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class JDBCTestsLauncher {
	@Test
	public void run() {
		JUnitCore.runClasses(
				JDBCAuditoriumDaoTest.class, 
				JDBCEventDaoTest.class,
				JDBCTicketDaoTest.class,
				JDBCUserDaoTest.class);
	}
}
