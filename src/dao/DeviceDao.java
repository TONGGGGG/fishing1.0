package dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import bean.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.hibernateUtils;


@Repository
public class DeviceDao {

	@Autowired
	SessionFactory sessionFactory;


	public List<Device> getDetectDeviceListByPid(String pid){
		Session session = sessionFactory.openSession();
		List<Device> list = session.createQuery("from Device where pond.pid=? and type.param.pid is null")
				.setParameter(0,pid)
				.list();
		session.close();
		return list;
	}

	public List<Device> getCulDeviceListByPid(String pid){
		Session session = sessionFactory.openSession();
		List<Device> list = session.createQuery("from Device where pond.pid=? and type.param.pid is not null")
				.setParameter(0,pid)
				.list();
		session.close();
		return list;
	}

	public List getDeviceList(DetachedCriteria criteria) {
		Session session = sessionFactory.openSession();
		List<Device> list = criteria.getExecutableCriteria(session).list();
		session.close();
		return list;
	}

	public Device getDeviceByDevice(String device) {
		Session session = sessionFactory.openSession();
		Device d = (Device)session.get(Device.class, device);
		session.close();
		return d;
	}

	public boolean validate(String device) {
		
		return true;
	}

	public void saveOrUpdateDevice(Device device) {
		Session session = hibernateUtils.getSession();
		
		session.merge(device);
		
		session.flush();
		session.close();
	}

	public void DeletDevice(String device) {
		//��ȡһ��session
		Session session = hibernateUtils.getSession();
		
		//Ϊ�ô�������һ������
		Transaction tr = session.beginTransaction();
		
		
		try {
			//��ȡ��ID��Ӧ�Ķ���
			Device d = (Device)session.get(Device.class,device);
			
			//ɾ���ö���
			session.delete(d);
			
			//�ύ����
			tr.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			
			throw e;
			
		} finally {
			session.close();
		}
	}




}
