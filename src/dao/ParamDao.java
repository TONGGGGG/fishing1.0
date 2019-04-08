package dao;

import bean.Param;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParamDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<Param> getParamList(){
        Session session = sessionFactory.openSession();
        List<Param> params = session.createQuery("from Param").list();
        session.close();
        return params;
    }
}
