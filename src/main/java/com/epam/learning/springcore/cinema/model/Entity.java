package com.epam.learning.springcore.cinema.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class Entity<PK> {
	
	protected PK id;
	
	public PK getId() {
		return id;
	}
	
	public void setId(PK id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		 return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
