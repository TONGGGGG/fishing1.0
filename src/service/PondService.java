package service;

import bean.*;
import dao.DataDao;
import dao.PondDao;
import dao.StatisticDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import statistic.DeviceStatistic;
import statistic.PondStatistic;
import util.DateUtil;
import util.HcharUtil;
import util.JsonUtil;
import util.ParamUtil;

import java.util.*;


@Service
public class PondService {

    @Autowired
    PondDao pondDao;

    @Autowired
    DataDao dataDao;

    @Autowired
    DeviceService deviceService;

    @Autowired
    PondStatistic pondStatistic;

    @Autowired
    DeviceStatistic deviceStatistic;

    @Autowired
    StatisticDao statisticDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //添加/修改鱼塘
    public void addPond(Pond pond){

        pondDao.saveOrUpdatePond(pond);
    }


    //添加/修改鱼塘
    public Pond getPond(String pid){
        return pondDao.getPondByPid(pid);
    }


//鱼塘主页
    public Map getAllPondMap(User user )throws Exception{
        List list = pondDao.getPondListByUid(user.getId());
        Map pondDeviceMap = new HashMap();
        for (Object obj:list) {
            Set<Device> devices = ((Pond)obj).getDevices();
            Map cre_paramMap =  ParamUtil.getCre_paramMap(((Pond) obj).getCreature().getCre_params());
            Map deviceDataMap = new HashMap();
            int onLineDeviceNum = 0;
            Iterator it1 = devices.iterator();
            while (it1.hasNext()){
                Device device = (Device) it1.next();
                String data = stringRedisTemplate.opsForValue().get("did:"+device.getDid());
                String mills = stringRedisTemplate.opsForValue().get("time_did:"+device.getDid());
                if(data==null||mills==null)continue;
                Long time = Long.parseLong(mills);
                Map pidMap = JsonUtil.data2PidMap(data);
                Map paramMap = ParamUtil.getParamMap(cre_paramMap,pidMap);
                Long difTime = System.currentTimeMillis()-time;
                onLineDeviceNum+=(difTime>3600000l)?0:1;
                Map deviceMap = new HashMap();
                deviceMap.put(device.getDname()+DateUtil.getHMS(difTime),ParamUtil.cNParamMap(paramMap));
                deviceDataMap.putAll(deviceMap);
            }
            if(onLineDeviceNum==0)
                deviceDataMap.put("state","0");
            else
                deviceDataMap.put("state",onLineDeviceNum==devices.size()?"1":"2");

            deviceDataMap.put("pid",((Pond)obj).getPid());

            pondDeviceMap.put(((Pond)obj).getPname(),deviceDataMap);

            //渔场的各种属性等。。。。
        }

        return pondDeviceMap;
    }


    //鱼塘详情
    public Map getPondMap(String pid,String date)throws Exception {
        Map pondMap = new HashMap();

        Pond pond = pondDao.getPondByPid(pid);
        Set<Device> devices = pond.getDevices();
        Map cre_paramMap = ParamUtil.getCre_paramMap(pond.getCreature().getCre_params());
        Map deviceDataMap = new HashMap();
        Iterator it1 = devices.iterator();
        while (it1.hasNext()){
            Device device = (Device) it1.next();
            if (device.getType().getParam()==null)
                deviceDataMap.putAll(deviceService.getOneDataMap(device,date,cre_paramMap));
        }
        //关键指标
        pondMap.put("index",deviceDataMap);
        //水质记录
        pondMap.put("data",getAllDataMap(pid,date,null));
        //设备信息
        pondMap.putAll(deviceService.getDetectCulDeviceMap(pond));
        //pid
        pondMap.put("pid",pond.getPid());
        //pname
        pondMap.put("pname",pond.getPname());
        //mode
        pondMap.put("mode",pond.getMode());
        //analysis
        pondMap.put("analysis",analysisMap(pond,date,null));
        return pondMap;
    }


    //历史记录---获取所有设备一段时间的所有数据的map
    public Map getPondAllDeviceMap(String pid,String beginTime,String endTime,boolean hchart) {
        Pond pond = pondDao.getPondByPid(pid);
        Set<Device> devices = pond.getDevices();
        Map allDeviceDataMap = new LinkedHashMap();
        Iterator it1 = devices.iterator();
        if (hchart==false){
            while (it1.hasNext()){
                Device device = (Device) it1.next();
                if (device.getType().getParam()==null){
                    Map deviceAllDataMap = deviceService.getDeviceAllDataMap(device,beginTime,endTime);
                    if (deviceAllDataMap!=null)
                        allDeviceDataMap.putAll(deviceAllDataMap);
                }
            }
            return allDeviceDataMap;
        }

        while (it1.hasNext()){
            Device device = (Device) it1.next();
            if (device.getType().getParam()==null) {
                Map deviceAllDataMap = deviceService.getDeviceAllDataMap(device,beginTime,endTime);
                if(deviceAllDataMap!=null)
                    allDeviceDataMap.put(device.getDid(), HcharUtil.getOneDeviceStaticCharMap(deviceAllDataMap));
            }
        }
        return allDeviceDataMap;

    }


    //某日水质记录
    public Map getAllDataMap(String pid,String beginTime,String endTime)throws Exception{
        Pond pond = pondDao.getPondByPid(pid);
        if (endTime==null)
            endTime = String.valueOf(Long.parseLong(beginTime)+86400000l);
        List<Data> dataList = dataDao.getAllData(pid,beginTime,endTime);

        LinkedHashMap map = new LinkedHashMap();
        for (Data data:dataList) {
            Map pidMap = JsonUtil.data2PidMap(data.getData());
            Map cre_paramMap = ParamUtil.getCre_paramMap(pond.getCreature().getCre_params());
            map.put(DateUtil.getHHmmss(data.getTime()),ParamUtil.cNParamMap(ParamUtil.getParamMap(cre_paramMap,pidMap)));
        }
        return map;
    }


    public Map analysisMap(Pond pond,String beginTime,String endTime)throws Exception{
        if (endTime==null)
            endTime = String.valueOf(Long.parseLong(beginTime)+86400000l);
        List list;
//        if (System.currentTimeMillis()-Long.parseLong(beginTime)>86400000l)
//            list = pondStatistic.getPondAverageStatisticData(pond.getPid(),beginTime);
//        else
            list = dataDao.getAllData(pond.getPid(),beginTime,endTime);
        if (list.size()==0)
            return null;
        Map averageDataMap = JsonUtil.averageData(list);
        Map cre_paramMap = ParamUtil.getCre_paramMap(pond.getCreature().getCre_params());
        return ParamUtil.analysisMap(pond.getCreature().getCname(),cre_paramMap,averageDataMap);
    }



    }




