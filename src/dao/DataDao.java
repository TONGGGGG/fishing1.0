package dao;

import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import bean.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.hibernateUtils;


@Repository
public class DataDao {

	@Autowired
	SessionFactory sessionFactory;



	//保存数据
	public void saveData(Data data){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(data);
		tx.commit();
		session.close();
	}

	//获取今日最新的一条
	public Data getOneNewData(String did ,long todayTimeMiles){

		Session session = sessionFactory.openSession();
		List list = session.createQuery("from Data where device.did = ? and time >= ? order by time desc")
				.setParameter(0,did)
				.setParameter(1,String.valueOf(todayTimeMiles))
				.list();
		session.close();
		return (Data) list.get(0);
	}

	//获取某个池塘的所有数据
	public List<Data> getAllData(String pid,String beginTime,String endTime){
		Session session = sessionFactory.openSession();
		List<Data> list = session.createQuery("from Data where device.pond.pid = ? and time between ? and ? order by time desc")
				.setParameter(0,pid)
				.setParameter(1,beginTime)
				.setParameter(2,endTime)
				.list();
		session.close();
		return list;
	}

	public List<Data> getOneHistoryData(String did ,long todayTimeMiles){
		Long time = todayTimeMiles+86400000L;
		Session session = sessionFactory.openSession();
		List<Data> list = session.createQuery("from Data where device.did = ? " +
				"and time between ? and ? order by time desc")
				.setParameter(0,did)
				.setParameter(1,String.valueOf(todayTimeMiles))
				.setParameter(2,String.valueOf(time))
				.list();
		session.close();
		return list;
	}

//	public Data getRealTimeDataByDevice(String device) {
//		Session session = hibernateUtils.getSession();
//
//		Query query = session.createQuery("select new Data(parm1,parm2,parm3,parm4,time) from Data where device.device = ? order by time desc ");
//
//		query.setParameter(0, device);
//
//		List list = query.list();
//
//		return (Data) list.get(0);
//	}

	public List<Data> getDataListByTime(String did,String beginTime,String endTime) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Data.class);
		criteria.add(Restrictions.between("time", beginTime, endTime));
		criteria.addOrder(Order.asc("time"));
		criteria.add(Restrictions.eq("device.did", did));
		List<Data> list = criteria.list();
		session.close();
		return list;
	}

}
