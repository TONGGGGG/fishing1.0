package dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import bean.Device;
import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.hibernateUtils;

@Repository
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;

	public User login(DetachedCriteria criteria) throws SQLException {
		
		Session session = sessionFactory.openSession();
		List<User> list = criteria.getExecutableCriteria(session).list();
		session.close();
		if(list.size()==0)
			return null;
		else
			return list.get(0);
			
	}

	public int checkEmail(String email){
		Session session = sessionFactory.openSession();
		List<Object> list= session.createQuery("from User where email = ?")
				.setParameter(0,email)
				.list();
		session.close();
		return list.size();
	}

	public User regist(User user) {

		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(user);
		tr.commit();
		session.close();
		return user;
	}

}
