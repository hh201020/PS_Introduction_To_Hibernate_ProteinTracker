package com.simpleprogrammer;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class Program {

	public static void main(String[] args){
		System.out.println("Helloooo");
		populateSampleData();
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
/*		
		Query query = session.createQuery("select alert from GoalAlert alert")
				.setFirstResult(2)		// Page starting from 2
				.setMaxResults(3);		// Page get 1 result at a time
*/
/*		Query query = session.getNamedQuery("AllGoalAlerts");
		List<GoalAlert> goalAlerts = query.list();
		for(GoalAlert goalAlert: goalAlerts){
			System.out.println(goalAlert.getMessage());
		}*/
		Criteria criteria = session.createCriteria(User.class);
		List<User> Users = criteria.list();
		for(User User: Users){
			System.out.println(User.getName());
		}		
		session.getTransaction().commit();
		session.close();
		
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
