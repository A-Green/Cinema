package com.epam.learning.springcore.cinema.aspects.counters.mapbased;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractSimpleCounter<K> {

	protected Map<K, Integer> counter = new ConcurrentHashMap<>();

	public synchronized void increaseCounter(K key, int increment) {
		if (key != null) {
			Integer count = counter.get(key);
			if (count == null) {
				counter.put(key, increment);
			} else {
				counter.put(key, new Integer(count.intValue() + increment));
			}
		}
	}

	public void increment(K key) {
		increaseCounter(key, 1);
	}

	public Integer getCount(K key) {
		return counter.get(key);
	}
}
