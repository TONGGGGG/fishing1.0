package inteltask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import service.PondService;
import statistic.PondStatistic;
import util.DateUtil;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

@Component
public class IntelTask {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    PondStatistic pondStatistic;

    @Scheduled(cron="0 0/3 * * * ?")
    public  void todayTask()throws Exception{
        //获取当日活跃设备和池塘
//        Set<String> dids = stringRedisTemplate.keys("did:*");
//        Set<String> pids = stringRedisTemplate.keys("pid:*");
//        statisticsDeviceH_L(dids);
//        statisticsPondAll(pids);
//
//        Set<String> time_dids = stringRedisTemplate.keys("time_did:*");
//////        //删除当日活跃设备和池塘
//        stringRedisTemplate.delete(dids);
//        stringRedisTemplate.delete(pids);
//        stringRedisTemplate.delete(time_dids);
    }

    //今日活跃设备最高最低值统计
    public void statisticsDeviceH_L(Set<String> dids) throws Exception{
        Long tomorrowDate = DateUtil.getTomorrowMills(System.currentTimeMillis());
        Iterator it = dids.iterator();
        while(it.hasNext()){
            pondStatistic.setDeviceStatisticData(((String) it.next()).substring(4),String.valueOf(tomorrowDate));
        }
    }
    //今日活跃池塘均值最高最低值统计
    public void statisticsPondAll(Set<String> pids)throws Exception{
        Long tomorrowDate = DateUtil.getTomorrowMills(System.currentTimeMillis());
        Iterator it = pids.iterator();
        while(it.hasNext()){
            pondStatistic.setPondStatisticData(((String) it.next()).substring(4),String.valueOf(tomorrowDate));
        }
    }

}
