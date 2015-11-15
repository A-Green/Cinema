package com.epam.learning.springcore.cinema.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.utils.Formatter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class JDBCUserDaoTest extends ContextSupportTest {

	@Autowired
	private UserDao userDao;

	private static User user;
	private static boolean setupDone = false;

	@Before
	public void init() {
		if (!setupDone) {
			user = context.getBean(User.class);
			user.setName("testUser");
			user.setEmail("testuser@test.com");
			user.setBirthday(Formatter.getCurrentDate());
			setupDone = true;
		}
	}

	@Test
	public void saveTest() {
		userDao.save(user);
		assertNotNull(user.getId());
	}

	@Test
	public void getByTest() {
		User testIdUser = userDao.getById(user.getId());
		assertEquals(user, testIdUser);

		User testNameUser = userDao.getByName(user.getName());
		assertEquals(user, testNameUser);

		User testEmailUser = userDao.getByEmail(user.getEmail());
		assertEquals(user, testEmailUser);
	}

	@Test
	public void removeTest() {
		int id = user.getId();
		userDao.remove(user.getId());
		User user = userDao.getById(id);
		assertNull(user);
	}
}
