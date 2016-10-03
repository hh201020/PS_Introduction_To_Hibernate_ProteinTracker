package com.simpleprogrammer;

import java.util.Date;
import java.util.Map.Entry;

import org.hibernate.Session;

public class Program {

	public static void main(String[] args){
		System.out.println("Helloooo");
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		session.beginTransaction();
		User user = new User();
		user.setName("Joe");
		user.getHistory().put("GL123", new UserHistory(new Date(), "Set name to Joe"));

		user.getProteinData().setGoal(250);
		user.getHistory().put("GL143",new UserHistory(new Date(), "Set the goal to 250"));
		session.save(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		User loadedUser = (User) session.get(User.class, 1);  //or session.load(User.class, 1), here 1 is id
		System.out.println("loaded user:" + loadedUser.getName());
		
		for(Entry<String,UserHistory> userHistory: loadedUser.getHistory().entrySet()){
			System.out.println("Key: " + userHistory.getKey());
			System.out.println(userHistory.getValue().getEntryTime().toString() + " " + userHistory.getValue().getEntry());
		}
		
		loadedUser.getProteinData().setTotal(loadedUser.getProteinData().getTotal() + 50);
		loadedUser.getHistory().put("GL333",new UserHistory(new Date(), "Added 50 protein"));
		session.getTransaction().commit();
		
		session.close();
		
		HibernateUtilities.getSessionFactory().close();
	}
}
