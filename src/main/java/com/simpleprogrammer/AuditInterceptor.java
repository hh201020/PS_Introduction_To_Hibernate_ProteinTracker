package com.simpleprogrammer;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor{

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("Saving an entity");
		return false;
	}

	@Override
	public void postFlush(Iterator entities) {
		System.out.println("After an entity flushed");
	}
	
}
