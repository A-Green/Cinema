package com.epam.learning.springcore.cinema.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;
import com.epam.learning.springcore.cinema.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	
	@Override
	public double getDiscount(User user, Event event, Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

}
