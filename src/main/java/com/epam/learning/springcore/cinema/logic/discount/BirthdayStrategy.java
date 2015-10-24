package com.epam.learning.springcore.cinema.logic.discount;

import java.util.Date;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;

public class BirthdayStrategy extends DiscountStrategy {

	public BirthdayStrategy() {
	}
	
	public BirthdayStrategy(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Override
	public double getDiscount(Event event, User user, Date date) {
		// TODO Auto-generated method stub
		return 0;
	}
}
