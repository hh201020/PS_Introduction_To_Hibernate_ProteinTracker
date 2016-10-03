package com.simpleprogramer;

import org.hibernate.Session;

public class Program {

	public static void main(String[] args){
		System.out.println("Helloooo");
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		session.beginTransaction();
		User user = new User();
		user.setName("Joe");
		user.setGoal(250);
		session.save(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		User loadedUser = (User) session.get(User.class, 1);  //or session.load(User.class, 1), here 1 is id
		System.out.println("loaded user:" + loadedUser.getName());
		loadedUser.setTotal(loadedUser.getTotal() + 50);
		session.getTransaction().commit();
		
		session.close();
		
		HibernateUtilities.getSessionFactory().close();
	}
}
