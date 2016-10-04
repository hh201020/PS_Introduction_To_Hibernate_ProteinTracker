package com.simpleprogrammer;

import java.util.Date;

import org.hibernate.Session;

public class Program {

	public static void main(String[] args){
		System.out.println("Helloooo");
		
		populateSampleData();
		
		HibernateUtilities.getSessionFactory().close();
	}

	private static void populateSampleData() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
		User joe = createUser("Joe", 500, 50, "Good job", "You made it");
		session.save(joe);
		User bob = createUser("Bob", 300, 20, "Gob", "Yit");
		session.save(bob);
		User amy = createUser("Amy", 200, 220, "GYY", "t");
		session.save(amy);
		
		session.getTransaction().commit();
		
		session.close();
	}

	private static User createUser(String name, int goal, int total, String ... alerts) {
		User user = new User();
		user.setName(name);
		user.getProteinData().setGoal(goal);
		user.addHistory(new UserHistory(new Date(), "Set the goal to " + goal ));
		user.getProteinData().setTotal(total);
		user.addHistory(new UserHistory(new Date(), "Set the total to " + total ));
		for(String alert : alerts){
			user.getGoalAlerts().add(new GoalAlert(alert));
		}
		return user;
	}
}
