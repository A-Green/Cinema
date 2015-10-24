package com.epam.learning.springcore.cinema.dao.impl;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.learning.springcore.cinema.dao.BaseDao;
import com.epam.learning.springcore.cinema.model.Entity;

public abstract class MapBaseDaoImpl<PK, T extends Entity<PK>> implements BaseDao<PK, T> {

	@Override
	public abstract Map<PK, T> getAll();
	
	@Override
	public T getById(PK id) {
		return (T) getAll().get(id);
	}

	@Override
	public T save(T entity) {
		if (entity != null) {
			return (T) getAll().put(entity.getId(), entity);
		}
		return null;
	}

	@Override
	public void remove(PK id) {
		getAll().remove(id);
	}
	
	@SuppressWarnings("unchecked")
	protected <valType> T fieldGetter(valType value, String methodName) {
		try {
			for (Entry<PK, T> entry : getAll().entrySet()) {
				T item = entry.getValue();
				Method m = item.getClass().getDeclaredMethod(methodName);
				if (value == (valType) m.invoke(item)) {
					return item;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
