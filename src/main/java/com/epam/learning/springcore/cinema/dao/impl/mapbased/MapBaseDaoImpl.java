package com.epam.learning.springcore.cinema.dao.impl.mapbased;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.epam.learning.springcore.cinema.dao.BaseDao;
import com.epam.learning.springcore.cinema.model.Entity;

public abstract class MapBaseDaoImpl<PK, T extends Entity<PK>> implements BaseDao<PK, T> {

	public abstract Map<PK, T> getEntityMap();
	
	@Override
	public T getById(PK id) {
		return (T) getEntityMap().get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T entity) {
		if (entity != null) {
			PK id = entity.getId();
			if (id == null){
				Random random = new Random();
				// FIXME: 0.5%: what happens when we'd reached upper bound?
				// It would be strange to manage those amount of objects using map-based dao =)
				while(getEntityMap().keySet().contains(id = (PK) new Integer(random.nextInt(10000000))));
				entity.setId(id);
			}
			getEntityMap().put(entity.getId(), entity);
			return entity;
		}
		return null;
	}

	@Override
	public void remove(PK id) {
		getEntityMap().remove(id);
	}
	
	@SuppressWarnings("unchecked")
	protected <valType> T fieldGetter(valType value, String methodName) {
		try {
			for (Entry<PK, T> entry : getEntityMap().entrySet()) {
				T item = entry.getValue();
				Method m = item.getClass().getDeclaredMethod(methodName);
				if (value.equals((valType) m.invoke(item))) {
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
