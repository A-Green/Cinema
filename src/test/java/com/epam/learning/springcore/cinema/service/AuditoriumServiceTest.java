package com.epam.learning.springcore.cinema.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Auditorium;
import com.epam.learning.springcore.cinema.service.exception.AuditoriumServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class AuditoriumServiceTest {

	@Autowired
	private AuditoriumService auditoriumSerivce;
	
	//check that auditoriums are loaded from property file
	@Test
	public void test() throws AuditoriumServiceException {
		List<Auditorium> auditoriums = auditoriumSerivce.getAuditoriums();
		assertNotNull(auditoriums);
		//check that exactly 3 auditoriums was loaded from property file
		assertTrue(auditoriums.size() == 3);
		
		//check auditory
		String auditName = "auditorium1";
		Auditorium aud1 = auditoriumSerivce.getByName(auditName);
		assertNotNull(aud1);
		assertEquals(auditoriumSerivce.getSeatsNumber(auditName), 100);
		List<Integer> vipSeats = auditoriumSerivce.getVipSeats(auditName);
		assertNotNull(vipSeats);
		assertTrue(vipSeats.size() == 10);
	}
}
