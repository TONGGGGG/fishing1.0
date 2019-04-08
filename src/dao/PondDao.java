package dao;


import bean.Pond;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PondDao {

    @Autowired
    SessionFactory sessionFactory;

    public void saveOrUpdatePond(Pond pond){
        Session session = sessionFactory.openSession();
        session.merge(pond);
        session.close();
    }

    public List getPondListByUid(String uid){
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Pond where user.id = ? ").setParameter(0,uid).list();
        session.close();
        return list;
    }

    public Pond getPondByPid(String pid){
        Session session = sessionFactory.openSession();
        Pond pond = (Pond) session.createQuery("from Pond where pid = ? ").setParameter(0,pid).list().get(0);
        session.close();
        return pond;
    }


}
