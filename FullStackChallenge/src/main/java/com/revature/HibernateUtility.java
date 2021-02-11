package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	private static Session session;
	public static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public Session getSession() { 
		if(session == null) {
			session = sf.openSession();
		}
		return session; 
	}
}
