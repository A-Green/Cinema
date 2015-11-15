package com.epam.learning.springcore.cinema.aspects.counters.jdbc;

import com.epam.learning.springcore.cinema.logic.discount.BirthdayStrategy;
import com.epam.learning.springcore.cinema.logic.discount.PurchasedStrategy;

public enum StrategyType {

	BIRTHDAY(BirthdayStrategy.class),
	PURCHASED(PurchasedStrategy.class);

	private final Class<?> mapClass;

	private StrategyType(Class<?> mapClass) {
		this.mapClass = mapClass;
	}

	public Class<?> getMapClass() {
		return mapClass;
	}

	public static StrategyType fromValue(Class<?> value) throws IllegalArgumentException {
		for (StrategyType e : StrategyType.values()) {
			if (value.equals(e.getMapClass())) {
				return e;
			}
		}
		throw new IllegalArgumentException("Unknown enum value :" + value);
	}
}
