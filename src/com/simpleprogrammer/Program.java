package com.simpleprogrammer;

import java.util.Date;

import org.hibernate.Session;

public class Program {

	public static void main(String[] args){
		System.out.println("Helloooo");
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		session.beginTransaction();
		User user = new User();
		user.setName("Joe");
		user.addHistory(new UserHistory(new Date(), "Set name to Joe"));

		user.getProteinData().setGoal(250);
		user.addHistory(new UserHistory(new Date(), "Set the goal to 250"));
		session.save(user);
		session.getTransaction().commit();

		session.beginTransaction();
		User loadedUser = (User) session.get(User.class, 1);  //or session.load(User.class, 1), here 1 is id
		System.out.println("loaded user:" + loadedUser.getName());
		
		for(UserHistory userHistory: loadedUser.getHistory()){
			System.out.println(userHistory.getEntryTime().toString() + " " + userHistory.getEntry());
		}
		
		loadedUser.getProteinData().setTotal(loadedUser.getProteinData().getTotal() + 50);
		loadedUser.addHistory(new UserHistory(new Date(), "Added 50 protein"));
		
		user.setProteinData(new ProteinData());
		
		session.getTransaction().commit();
		
		session.close();
		
		HibernateUtilities.getSessionFactory().close();
	}
}
