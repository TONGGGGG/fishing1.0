package util;

import java.util.*;

import bean.Cre_param;
import bean.Data;
import bean.Pond_statistic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.xml.XMLSerializer;
import org.junit.Test;

/**
 * 处理json数据格式的工具类
 * 
 * @Date 2013-3-31
 * @version 1.0
 */
public class JsonUtil {

	public static String parseObjToJson(Object object) {
		String string = null;
		try {
			//string = JSON.toJSONString(object);
			string = com.alibaba.fastjson.JSONObject.toJSONString(object);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return string;
	}

	/**
	 * 将数组转换成String类型的JSON数据格式
	 * 
	 * @param objects
	 * @return
	 */
	public static String array2json(Object[] objects){
		
		JSONArray jsonArray = JSONArray.fromObject(objects);
		return jsonArray.toString();
		
	}
	
	/**
	 * 将list集合转换成String类型的JSON数据格式
	 * 
	 * @param list
	 * @return
	 */
	public static String list2json(List list){
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
		
	}
	
	/**
	 * 将map集合转换成String类型的JSON数据格式
	 * 
	 * @param map
	 * @return
	 */
	public static String map2json(Map map){
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();
		
	}
	
	/**
	 * 将Object对象转换成String类型的JSON数据格式
	 * 
	 * @param object
	 * @return
	 */
	public static String object2json(Object object){
		
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
		
	}


	@Test
	public static Map data2Map(String data){
		String[] s= data.split("\\|");
		HashMap<String,String> map = new LinkedHashMap();
		for (String str: s) {
			String name = ParamUtil.getParamName(str.substring(0,3));
			map.put(name,str.substring(3));
		}

		return map;

	}
	public static Map data2PidMap(String data){
		String[] s= data.split("\\|");
		HashMap<String,String> map = new LinkedHashMap();
		for (String str: s) {
			map.put(str.substring(0,3),str.substring(3));
		}

		return map;

	}

	public static Map data2MapByParamMap(String data,Map paramMap){
		String[] s= data.split("\\|");
		HashMap<String,String> map = new LinkedHashMap();
		for (String str: s) {
			String pid = str.substring(0,3);
			Double value = Double.valueOf(str.substring(3));
			String name = ParamUtil.getParamName(str.substring(0,3));
			if ((Double)paramMap.get("H"+pid)<value)
				map.put("s"+name,str.substring(3));
			else if ((Double)paramMap.get("L"+pid)>value)
				map.put("s"+name,str.substring(3));
		}
		return map;
	}

	public static Map forecastMap(double[] ryABR, double[] phABR , Map cre_paramMap)throws Exception{

		Double hph = Double.valueOf((String) cre_paramMap.get("H001"));
		Double lph = Double.valueOf((String) cre_paramMap.get("L001"));
		Double hry = Double.valueOf((String) cre_paramMap.get("H004"));
		Double lry = Double.valueOf((String) cre_paramMap.get("L004"));
		int x = DateUtil.getNowMills();
		ArrayList timelist = new ArrayList();
		ArrayList phlist = new ArrayList();
		ArrayList rylist = new ArrayList();

		LinkedHashMap msgMap = new LinkedHashMap();
		String msg = "";
		for (int i = x+60000; i < 86400000; i+=1800000) {
			String time = DateUtil.getDateByNowMills(i);
			Double ph = phABR[0]*x+phABR[1];
			Double ry = ryABR[0]*x+ryABR[1];
			if (ph>hph)
				msgMap.put(time,msgMap.get(time)==null?"PH或将高于正常值;":msgMap.get(time)+"PH或将高于正常值;");
			else if (ph<lph)
				msgMap.put(time,msgMap.get(time)==null?"PH或将低于正常值;":msgMap.get(time)+"PH或将低于正常值;");
			if (ry>hry)
				msgMap.put(time,msgMap.get(time)==null?"溶解氧或将高于正常值;":msgMap.get(time)+"溶解氧或将高于正常值;");
			else if (ph>hph)
				msgMap.put(time,msgMap.get(time)==null?"溶解氧或将低于正常值;":msgMap.get(time)+"溶解氧或将低于正常值;");

			timelist.add(time);
			phlist.add(ParamUtil.save2point(ph));
			rylist.add(ParamUtil.save2point(ry));
		}
		Map map = new HashMap();
		map.put("time",timelist);
		map.put("ph",phlist);
		map.put("ry",rylist);
		map.put("msg",msgMap);
		return map;
	}



	public static Map historyData(List<Data> datas){
		HashMap<String,String> map = new HashMap();
		for (Data data:datas) {
			String[] s= data.getData().split("\\|");
			for (String str: s) {
				String key = str.substring(0,3);
				Double value = Double.parseDouble(str.substring(3));

				String h_key="最高"+key;
				Double h_value=null;
				if (map.get(h_key)!=null) {
					h_value = Double.parseDouble(map.get(h_key));
				}

				String l_key="最低"+key;
				Double l_value=null;
				if (map.get(l_key)!=null) {
					l_value = Double.parseDouble(map.get(l_key));
				}

				if (h_value==null&&l_value==null){
					map.put(h_key,String.valueOf(value));
					map.put(l_key,String.valueOf(value));
				}else if (value>h_value){
					map.put(h_key,String.valueOf(value));
				}else if (value<l_value){
					map.put(l_key,String.valueOf(value));
				}
			}
		}
		return map;

	}


	public static Map averageData(List list){

		if (list.get(0) instanceof Pond_statistic){
			List<Pond_statistic> pond_statisticList = list;
			Map averageDataMap = new HashMap();
			for (Pond_statistic pond_statistic:pond_statisticList) {
				averageDataMap.put(pond_statistic.getParam().getPid(),pond_statistic.getValue());
			}
			return averageDataMap;
		}

		List<Data> datas = list;
		HashMap<String,String> map = new HashMap();
		HashMap<String,Integer>  stateMap = new HashMap();
		for (Data data:datas) {
			String[] s= data.getData().split("\\|");
			for (String str: s) {
				String key = str.substring(0,3);
				Double value = Double.parseDouble(str.substring(3));
				Double values = Double.parseDouble(map.get(key)==null?"0":map.get(key));
				map.put(key,String.valueOf(value+values));
				stateMap.put(key,stateMap.get(key)==null?1:stateMap.get(key)+1);
			}
		}
		Iterator it = map.keySet().iterator();
		while (it.hasNext()){
			String key = (String) it.next();
			map.put(key,String.valueOf(Double.valueOf(map.get(key))/stateMap.get(key)));
		}
		return map;

	}

	
	/**
	 * 将XML数据格式转换成String类型的JSON数据格式
	 * 
	 * @param xml
	 * @return
	 */
	public static String xml2json(String xml){
		
		JSONArray jsonArray = (JSONArray) new XMLSerializer().read(xml);
		return jsonArray.toString();
		
	}
	
	/**
	  * 除去不想生成的字段（特别适合去掉级联的对象）
	  *
	  * @param excludes
	  * @return
	*/
	public static JsonConfig configJson(String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(true);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return jsonConfig;
	}
	
}
