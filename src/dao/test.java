package dao;

import org.hibernate.Session;

import bean.Device;
import bean.User;
import util.hibernateUtils;

public class test {
	public static void main(String[] args) {
		saveOrUpdateDevice();
	}
	public static void saveOrUpdateDevice() {
		Session session = hibernateUtils.getSession();

		
//		User user = session.get(User.class, "heiyi");
		
//		user.setPasswd("111");
//		user.setId("tom");
//		session.update(user);
		
		session.flush();
		session.close();
	}
}
