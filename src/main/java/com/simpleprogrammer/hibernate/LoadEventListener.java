package com.simpleprogrammer.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.LoadEvent;

public class LoadEventListener extends DefaultLoadEventListener {

	@Override
	public void onLoad(LoadEvent event, LoadType arg1) throws HibernateException {
		System.out.println("Entity loaded:");
		System.out.println(event.getEntityId());
	}

}
