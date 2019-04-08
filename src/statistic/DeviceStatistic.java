package statistic;

import bean.Device_statistic;
import dao.StatisticDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceStatistic {
    @Autowired
    StatisticDao statisticDao;

    public Map getSomedayHLMap(String did,String mills){
        List<Device_statistic> list = statisticDao.getDeviceSomedayStatistics(did,mills);
        if (list.size()==0)
            return null;
        Map hLMap = new HashMap();
        for (Device_statistic device_statistic:list) {
            String type = device_statistic.getType();
            switch (type){
                case "1":
                    hLMap.put("最高"+device_statistic.getParam().getPid(),device_statistic.getValue());
                    break;
                case "-1":
                    hLMap.put("最低"+device_statistic.getParam().getPid(),device_statistic.getValue());
                    break;
            }
        }
        return hLMap;
    }
}
