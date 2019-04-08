package service;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import bean.Data;
import bean.Device;
import bean.Pond;
import correspondence.ReceiveData;
import dao.DataDao;
import dao.DeviceDao;
import dao.PondDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import statistic.DeviceStatistic;
import util.DateUtil;
import util.HcharUtil;
import util.JsonUtil;
import util.ParamUtil;


@Service
public class DeviceService {

	@Autowired
	DataDao dataDao;

	@Autowired
	PondDao pondDao;

	@Autowired
	PondService pondService;

	@Autowired
	DeviceDao deviceDao;

	@Autowired
	ReceiveData receiveData;

	@Autowired
	DeviceStatistic deviceStatistic;

	@Autowired
	StringRedisTemplate stringRedisTemplate;


	//开十分钟对应的调控设备
	public void turnOnSomeDevice(Set<Device> devices,String pid){
		Iterator it = devices.iterator();
		while (it.hasNext()){
			Device device = (Device)it.next();
			try {
				String state = stringRedisTemplate.opsForValue().get("state_did"+device.getDid());
				if (device.getType().getParam().getPid().equals(pid)&&(state==null||state.equals("off"))){
						receiveData.sendService("on",stringRedisTemplate.opsForValue().get("ip_did:"+device.getDid()));
						stringRedisTemplate.opsForValue().set("state_did:"+device.getDid(),"on",600,TimeUnit.SECONDS);
				}
			}catch (Exception e){
			}
		}
	}



	public List getDeviceList(DetachedCriteria criteria) throws SQLException {
		
		return deviceDao.getDeviceList(criteria) ;
	}
	public Map changeState(Device device) throws Exception {

		boolean b = receiveData.sendService(device.getState(),stringRedisTemplate.opsForValue().get("ip_did:"+device.getDid()));
		Map operateMap = new HashMap();
		operateMap.put("operate",b==true?1:0);
		return operateMap;
	}



	public Map getDeviceAllDataMap(Device device, String beginTime, String endTime){
		List<Data> dataList = dataDao.getDataListByTime(device.getDid(),beginTime,endTime);
		if (dataList.size()==0)
			return null;
		LinkedHashMap deviceDatamap = new LinkedHashMap();
		LinkedHashMap timeDatamap = new LinkedHashMap();
		for (Data data:dataList) {
			timeDatamap.put(DateUtil.getNowDate(data.getTime()),JsonUtil.data2PidMap(data.getData()));
		}
		deviceDatamap.put(device.getDname(),timeDatamap);
		return deviceDatamap;
	}

	//设备历史指标---获取所有设备一段时间内每天的最高值最低值map
	public Map getPondAllDeviceHLMap(String pid,String beginTime,String endTime,boolean hchart) throws Exception{
		Pond pond = pondDao.getPondByPid(pid);
		Map cre_paramMap = ParamUtil.getCre_paramMap(pond.getCreature().getCre_params());
		Set<Device> devices = pond.getDevices();
		Map allDeviceHL = new LinkedHashMap();
		Iterator it1 = devices.iterator();
		if (hchart==false) {
			while (it1.hasNext()) {
				Device device = (Device) it1.next();
				allDeviceHL.putAll(getDeviceHLMap(device, beginTime, endTime, cre_paramMap));
			}
			return allDeviceHL;
		}
		while (it1.hasNext()) {
			Device device = (Device) it1.next();
			allDeviceHL.put(device.getDid(), HcharUtil.getOneDeviceStaticCharMap(getDeviceHLMap(device, beginTime, endTime, cre_paramMap)));
		}
		return allDeviceHL;
	}


	//获取某设备一段时间内每日的最高最低值
	public Map getDeviceHLMap(Device device, String beginTime, String endTime ,Map cre_paramMap)throws Exception{
		Long begin = Long.parseLong(beginTime);
		Long end = Long.parseLong(endTime);
		LinkedHashMap timeDataMap = new LinkedHashMap();
		for (Long i = begin; i <= end; i+=86400000l) {
			Map oneDataMap = getOneDataMap(device,String.valueOf(i),cre_paramMap);
			if (oneDataMap!=null)
				timeDataMap.putAll((Map) oneDataMap.get(device.getDname()));
		}
		Map deviceHLMap = new HashMap();
		deviceHLMap.put(device.getDname(),timeDataMap);
		return deviceHLMap;
	}

	//关键指标---某设备实时数据或历史某一天最高和最低数据
	public Map getOneDataMap(Device device,String date,Map cre_paramMap)throws Exception{
		Map deviceDataMap = new HashMap();
		long timeMiles = DateUtil.getTodayZeroMills(date);
		//查找今日的数值
		if (System.currentTimeMillis()-timeMiles<86400000L) {
			//从redis中获取今日最新值
			String data =  stringRedisTemplate.opsForValue().get("did:"+device.getDid());
			String data_time =  stringRedisTemplate.opsForValue().get("time_did:"+device.getDid());
			if (data != null&&data_time!=null) {
				String hms = DateUtil.getHMS(System.currentTimeMillis()-Long.parseLong(data_time));
				Map pidMap = JsonUtil.data2PidMap(data);
				Map paramMap = ParamUtil.getParamMap(cre_paramMap,pidMap);
				if (device.getType().getTypeName().equals("综合监测设备")) {
					deviceDataMap.put(device.getDname() + hms, paramMap);
				} else {
					deviceDataMap.put(device.getDname() + hms, paramMap);
				}
			}else
				return null;
		}else{
			//查找历史某天数值
//            List<Data> dataList = dataDao.getOneHistoryData(device.getDid(),timeMiles);
			Map map = deviceStatistic.getSomedayHLMap(device.getDid(),String.valueOf(timeMiles));
			if (map != null) {
				Map paramMap = ParamUtil.getParamMap(cre_paramMap,map);
				if (device.getType().getTypeName().equals("综合监测设备")) {
					Map timeParamMap = new HashMap();
					timeParamMap.put(DateUtil.getNowDate(timeMiles).substring(0,10),ParamUtil.cNParamMap(paramMap));
					deviceDataMap.put(device.getDname(), timeParamMap);
				} else {
					Map timeParamMap = new HashMap();
					timeParamMap.put(DateUtil.getNowDate(timeMiles).substring(0,10),ParamUtil.cNParamMap(paramMap));
					deviceDataMap.put(device.getDname(), timeParamMap);
				}
			}
			else
				return null;
		}
		return  deviceDataMap;
	}


	public String registDevice(Device device) {
		//验证该设备是否存在
//		if(!dao.validate(device.getDevice()))
//			return "请输入正确的设备号！";
		//验证该设备是否已经被注册
		if(deviceDao.getDeviceByDevice(device.getDid())!=null)
			return "已注册的设备！";
		//持久化
		deviceDao.saveOrUpdateDevice(device);
		return "注册成功~";
	}

	public String upDateDevice(Device device) {
		deviceDao.saveOrUpdateDevice(device);
		return "修改成功~";
	}

	public String DeletDevice(String device) {
		deviceDao.DeletDevice(device);
		return "删除成功~";
	}

	public Device getDevice(String device) {
		return deviceDao.getDeviceByDevice(device);
	}
	public Map getDetectCulDeviceMap(Pond pond){
		Map deviceMap = new HashMap();
		List<Device> deviceList = deviceDao.getDetectDeviceListByPid(pond.getPid());
		Map detectDeviceMap = new HashMap();
		for (Device device : deviceList) {
			Long time = Long.parseLong(stringRedisTemplate.opsForValue().get("time_did:"+device.getDid()));
			Map map =new HashMap();
			map.put(device.getDid(),System.currentTimeMillis()-time>3600000l?0:1);
			detectDeviceMap.put(device.getDname(),map);
		}
		deviceList = deviceDao.getCulDeviceListByPid(pond.getPid());
		Map culDeviceMap = new HashMap();
		for (Device device : deviceList) {
			String state = stringRedisTemplate.opsForValue().get("state_did:"+device.getDid());
			if (state!=null){
				int sta = state.equals("on")?1:0;
				Map idStateMap = new HashMap();
				idStateMap.put(device.getDid(),sta);
				culDeviceMap.put(device.getDname(),idStateMap);
			}
			else{
				Map idStateMap = new HashMap();
				idStateMap.put(device.getDid(),-1);
				culDeviceMap.put(device.getDname(),idStateMap);
			}
		}

		deviceMap.put("detect_device",detectDeviceMap);
		deviceMap.put("cul_device",culDeviceMap);
		return deviceMap;
	}
}
