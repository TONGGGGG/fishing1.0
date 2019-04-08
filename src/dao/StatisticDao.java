package dao;

import bean.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StatisticDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    SessionFactory sessionFactory;

    public void bulkSavePondStatistics(List<Pond_statistic> pond_statisticList){
        final List<Pond_statistic> tempBpplist = pond_statisticList;
        String sql="insert into pond_statistics(PID,PARAM_ID,VALUE,TYPE,TIME)" +
                " values(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public int getBatchSize() {
                return tempBpplist.size();
            }
            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                ps.setString(1, tempBpplist.get(i).getPid());
                ps.setString(2, tempBpplist.get(i).getParam_id());
                ps.setString(3, tempBpplist.get(i).getValue());
                ps.setString(4, tempBpplist.get(i).getType());
                ps.setString(5, tempBpplist.get(i).getTime());
            }
        });

//        Session session = sessionFactory.openSession();
//        Transaction tx=session.beginTransaction();
////            for (int i = 0; i < pond_statisticList.size(); i++) {
////                Pond_statistic pond_statistic = pond_statisticList.get(i);
////                String sql = "insert into pond_statistics(PID,PARAM_ID,VALUE,TYPE,TIME) values('" + pond_statistic.getPid() + "','" + pond_statistic.getParam_id() + "','" + pond_statistic.getValue() + "','" + pond_statistic.getType() + "','" + pond_statistic.getTime() + "')";
////                String sql = "insert into pond_statistics values('001','001','6.8','0','1554220800000')";
////                SQLQuery query = session.createSQLQuery(sql);
////                query.executeUpdate();
//
//        Pond pond=new Pond();pond.setPid("001");
//        Param param =new Param(); param.setPid("001");
//        session.save( new Pond_statistic(  pond ,param,"6.8","0","1554220800000"));
//        tx.commit();
////            }
//
//            session.close();
    }

    public void bulkSaveDeviceStatistics(List<Device_statistic> device_statisticList){
//        try{
//            for ( int i=0; i<device_statisticList.size(); i++ ) {
//                Device_statistic device_statistic = device_statisticList.get(i);
//                jdbcTemplate.execute("insert into device_statistics(DID,PARAM_ID,VALUE,TYPE,TIME)" +
//                        " values('"+device_statistic.getDid()
//                        +"','"+device_statistic.getParamId()
//                        +"','"+device_statistic.getValue()
//                        +"','"+device_statistic.getType()
//                        +"','"+device_statistic.getTime()
//                        +"')");
////                if ( i % 20 == 0 ) { //单次批量操作的数目为20
////                    session.flush(); //清理缓存，执行批量插入20条记录的SQL insert语句
////                    session.clear(); //清空缓存中的对象
////                }
//            }
//            jdbcTemplate.execute("create table temps(id int primary key,name varchar(32))");
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//
//        }
        final List<Device_statistic> tempBpplist = device_statisticList;
        String sql="insert into device_statistics(DID,PARAM_ID,VALUE,TYPE,TIME)" +
                " values(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public int getBatchSize() {
                return tempBpplist.size();
            }
            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {
                ps.setString(1, tempBpplist.get(i).getDid());
                ps.setString(2, tempBpplist.get(i).getParamId());
                ps.setString(3, tempBpplist.get(i).getValue());
                ps.setString(4, tempBpplist.get(i).getType());
                ps.setString(5, tempBpplist.get(i).getTime());
            }
        });
    }

    public List<String> getParamId(String pid ,String beginTime ,String endTime){
        String sql = "select distinct PARAM_ID from pond_statistics where PID = '"+pid+"' and TIME between "+beginTime+" and "+endTime;
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql,String.class);
    }

    public List<Double> getParamValue(String pid,String param_id,String type ,String beginTime ,String endTime){
        String sql = "select VALUE from pond_statistics where PID = '" +pid
                +"' and PARAM_ID = '"+param_id
                +"' and TYPE = '"+type
                +"' and TIME between " +beginTime +" and "+endTime+" order by time desc";
        return jdbcTemplate.queryForList(sql,Double.class);
    }

    public List<String> getParamTime(String pid,String param_id,String beginTime ,String endTime){
        String sql = "select distinct TIME from pond_statistics where PID = '" +pid
                +"' and PARAM_ID = '"+param_id
                +"' and TIME between " +beginTime +" and "+endTime+" order by time desc";
        return jdbcTemplate.queryForList(sql,String.class);
    }


    public List<Pond_statistic> getPondAverageStatisticData(String pid,String beginTime){
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Pond_statistic where pond.pid = ? and time = ? and type = ?")
                .setParameter(0,pid)
                .setParameter(1,beginTime)
                .setParameter(2,"0")
                .list();
        session.close();
        return list;
    }

    public List<Device_statistic> getDeviceSomedayStatistics(String did,String mills){
        Session session = sessionFactory.openSession();
        List list = session.createQuery("from Device_statistic where device.did = ? and time = ?")
                .setParameter(0,did)
                .setParameter(1,mills)
                .list();
        session.close();
        return list;
    }

}
