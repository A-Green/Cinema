package com.epam.learning.springcore.cinema.dao.impl.mapbased;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.epam.learning.springcore.cinema.dao.UserDao;
import com.epam.learning.springcore.cinema.model.User;

@Repository
public class UserDaoImpl extends MapBaseDaoImpl<Integer, User> implements UserDao {

	private static Map<Integer,User> users;
	
	public  UserDaoImpl() {
		users = new HashMap<>();
	}

	@Override
	public User getById(Integer id) {
		return users.get(id);
	}

	@Override
	public User getByEmail(String email) {
		return fieldGetter(email, "getEmail");
	}

	@Override
	public User getByName(String name) {
		return fieldGetter(name, "getName");
	}

/*	@Override
	public List<Ticket> getBookedTickets(Integer userId) {
		User user = users.get(userId);
		if (user != null) {
			return user.getBookedTickets();
		}
		return null;
	}*/

	@Override
	public Map<Integer, User> getEntityMap() {
		return users;
	}
/*
	@Override
	public void bookTicket(User user, Ticket ticket) {
		User registeredUser = users.get(user.getId());
		if (registeredUser != null) {
			registeredUser.getBookedTickets().add(ticket);
		}
	}

	@Override
	public List<Ticket> getTicketsForEvent(Event event, Date date) {
		List<Ticket> bookedTickets = new ArrayList<Ticket>();
		for (Integer userId: users.keySet()){
			List<Ticket> userTickets = users.get(userId).getBookedTickets();
			if (userTickets != null) {
				for (Ticket ticket: userTickets) {
					if (ticket.getEvent().getId() == event.getId() 
							&& date.compareTo(ticket.getEventDate()) == 0) {
						bookedTickets.add(ticket);
					}
				}
			}
		}
		return bookedTickets;
	}*/

	@Override
	public User registerUser(User user) {
		if (user != null && user.getId() == null) {
			Random random = new Random();
			Integer id;
			while(getEntityMap().keySet().contains(id = new Integer(random.nextInt(100000))));
			user.setId(id);
			users.put(id, user);
		}
		return user;
	}
}