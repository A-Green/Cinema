package com.epam.learning.springcore.cinema.logic.discount;

import java.util.Date;

import com.epam.learning.springcore.cinema.model.Event;
import com.epam.learning.springcore.cinema.model.User;

public class PurchasedStrategy extends DiscountStrategy {

	public PurchasedStrategy() {
	}
	
	public PurchasedStrategy(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Override
	public double getDiscount(Event event, User user, Date date) {
		double discount = 0;
		if (user != null) {
			if ((user.getBookedTickets().size() + 1) % 10 == 0) {
				discount =  event.getBaseTicketPrice() / 100 * discountPercent;
			}
		}
		return discount;
	}
}
