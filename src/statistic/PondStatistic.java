package statistic;

import bean.Data;
import bean.Device;
import bean.Device_statistic;
import bean.Pond_statistic;
import dao.DataDao;
import dao.StatisticDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import util.DateUtil;
import util.HcharUtil;
import util.JsonUtil;
import util.ParamUtil;

import java.util.*;

@Service
public class PondStatistic {
    @Autowired
    DataDao dataDao;
    @Autowired
    StatisticDao statisticDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //统计池塘当天所有数据  最高最低(用于关键指标/历史指标)   均值(用于水质分析) 并保存到池塘统计表
    public void setPondStatisticData(String pid,String beginTime)throws Exception{
        String endTime = String.valueOf(Long.parseLong(beginTime)+86400000l);
        List<Data> dataList = dataDao.getAllData(pid,beginTime,endTime);
        List<Pond_statistic> list = new ArrayList();
        Map averageMap = JsonUtil.averageData(dataList);
        Map h_LMap = JsonUtil.historyData(dataList);

        Iterator it = averageMap.keySet().iterator();
        while(it.hasNext()){
            String param = String.valueOf(it.next());
            list.add(new Pond_statistic(pid,param,(String) averageMap.get(param),"0",beginTime));
        }

        it = h_LMap.keySet().iterator();
        while(it.hasNext()){
            String param = String.valueOf(it.next());
            list.add(new Pond_statistic(pid,param.substring(2),(String) h_LMap.get(param),(param.substring(0,2)).equals("最高")?"1":"-1",beginTime));
        }
        statisticDao.bulkSavePondStatistics(list);
    }

//    public  List<Pond_statistic> getPondAverageStatisticData(String pid,String beginTime){
////        return statisticDao.getPondAverageStatisticData(pid,beginTime);
//    }



    //历史指标---统计设备当天所有数据最高最低值并保存到设备统计表（每个设备一个图表）
    public void setDeviceStatisticData(String did,String beginTime)throws Exception{
        String endTime = String.valueOf(Long.parseLong(beginTime)+86400000l);
        List<Data> dataList = dataDao.getDataListByTime(did,beginTime,endTime);
        List<Device_statistic> list = new ArrayList();
        Map h_LMap = JsonUtil.historyData(dataList);
        Iterator  it = h_LMap.keySet().iterator();
        while(it.hasNext()){
            String param = String.valueOf(it.next());

            list.add(new Device_statistic(did,param.substring(2),(String) h_LMap.get(param),(param.substring(0,2)).equals("最高")?"1":"-1",beginTime));
        }
        statisticDao.bulkSaveDeviceStatistics(list);
    }

    public Map getPondIndex(String pid ,String beginTime ,String endTime,boolean hChart){
//        Long begin = Long.parseLong(beginTime);
//        Long end = Long.parseLong(endTime);
        LinkedHashMap paramMap = new LinkedHashMap();
        //查询该时间段活动参数
        List<String> paramIdList = statisticDao.getParamId(pid,beginTime,endTime);
        for (String paramId:paramIdList) {
            LinkedHashMap paramDataMap = new LinkedHashMap();
            paramDataMap.put("均值",statisticDao.getParamValue(pid,paramId,"0",beginTime,endTime).toArray());
            paramDataMap.put("最高值",statisticDao.getParamValue(pid,paramId,"1",beginTime,endTime).toArray());
            paramDataMap.put("最低值",statisticDao.getParamValue(pid,paramId,"-1",beginTime,endTime).toArray());
            paramDataMap.put("time",DateUtil.timeArray2DateArray(statisticDao.getParamTime(pid,paramId,beginTime,endTime).toArray()));
            paramDataMap.put("pname",stringRedisTemplate.opsForHash().get("paramMap",paramId));
            paramMap.put( paramId,paramDataMap);
        }
        if (hChart)
            return HcharUtil.getParamValueMap(paramMap);
        return paramMap;
    }

}
