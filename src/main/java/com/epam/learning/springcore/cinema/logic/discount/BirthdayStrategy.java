package com.epam.learning.springcore.cinema.logic.discount;

import java.util.Date;

import org.joda.time.DateTime;

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
		double discount = 0;
		if (user == null || user.getBirthday() == null) {
			return discount;
		}
		
		DateTime userBirthday = new DateTime(user.getBirthday());
		DateTime currentDate = new DateTime();
		double basePrice = event.getBaseTicketPrice();

		if (userBirthday.getMonthOfYear() == currentDate.getMonthOfYear() &&
				userBirthday.getDayOfMonth() == currentDate.getDayOfMonth()){
			discount = basePrice / 100 * discountPercent;
		}
	
		return discount;
	}
}
