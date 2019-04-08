package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateUtils {
	static Configuration CONFIG;
	static SessionFactory SESSIONFACTORY;
	static{
		CONFIG=new Configuration();
		SESSIONFACTORY=CONFIG.configure().buildSessionFactory();
	}
	
	public static Session getSession(){
		return SESSIONFACTORY.openSession();
	}

	public static Session getCurrentSession() {
		
		return SESSIONFACTORY.getCurrentSession();
	}
}
