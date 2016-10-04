package com.simpleprogrammer;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtilities {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	static
	{
		try{
			Configuration configuration = new Configuration().setInterceptor(new AuditInterceptor()).configure();
			
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		catch(HibernateException hibernateException){
			System.out.println("Problem creating session factory: " + hibernateException.getMessage());
			hibernateException.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
