package com.simpleprogrammer.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Program {

	public static void main(String[] args){
		System.out.println("Helloooo");
		populateSampleData();
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.enableFilter("nameFilter").setParameter("name", "j%");
		session.beginTransaction();
		
//		Query query = session.createSQLQuery("select * from Users").addEntity(User.class);
		Query query = session.createQuery("from User");
		List<User> users = query.list();
		for(User user: users) {
			System.out.println(user.getName());
		}
		
		User u = (User)session.load(User.class, 1);
		System.out.println(u.getName());
		
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
/*		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.or(
						Restrictions.eq("name", "Joe"), 
						Restrictions.eq("name", "Bob")
				)).setProjection(Projections.projectionList()
						.add(Projections.property("id"))
						.add(Projections.property("name"))
				);
		
		List<Object[]> results = criteria.list();
		for (Object[] result : results) {
			for (Object res : result) {
				System.out.println(res.toString());
			}
		}	*/
		
/*		Query query = session.createQuery("update ProteinData pd set pd.total = 0");
		query.executeUpdate();
		*/
/*		
		Criteria criteria = session.createCriteria(User.class);
		ScrollableResults users = criteria.setCacheMode(CacheMode.IGNORE).scroll();  //Using cursor in database
		int count = 0;
		while(users.next()){
			User user = (User) users.get(0);
			user.setProteinData(new ProteinData());
			session.save(user);
			if(++count % 2 == 0) {	// Manual batching
				session.flush();
				session.clear();
			}
			System.out.println(user.getName());
		}*/
		
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
