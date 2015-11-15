package com.epam.learning.springcore.cinema.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.Auditorium;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class JDBCAuditoriumDaoTest extends ContextSupportTest {
	
	//Assuming that aud1 and aud1 exists (populated from populate-db.sql)
	private int aud1SeatNumber = 100;
	private int aud2SeatNumber = 50;
	
	@Autowired
	private AuditoriumDao auditoriumDao;
	
	@Test
	public void getByNameTest() {
		Auditorium aud1 = auditoriumDao.getByName("aud1");
		assertNotNull(aud1);
		Auditorium aud2 = auditoriumDao.getByName("doesNotExist");
		assertNull(aud2);
	}
	
	@Test
	public void getAuditoriumsTest() {
		List<Auditorium> auds = auditoriumDao.getAuditoriums();
		assertTrue(auds.size() == 2);
	}
	
	@Test
	public void getSeatNumberTest() {
		assertEquals(aud1SeatNumber, auditoriumDao.getSeatsNumber("aud1"));
		assertEquals(aud2SeatNumber, auditoriumDao.getSeatsNumber("aud2"));
	}
	
	@Test
	public void getVipSeatsTest() {
		List<Integer> expected = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
		List<Integer> aud1Vips = auditoriumDao.getVipSeats("aud1");
		assertEquals(expected, aud1Vips);
		
		List<Integer> aud2Vips = auditoriumDao.getVipSeats("aud2");
		assertEquals(5, aud2Vips.size());
		
		assertTrue(auditoriumDao.getVipSeats("asdf").isEmpty());
	}
}
