package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HcharUtil {
    static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate ){
        HcharUtil.stringRedisTemplate=stringRedisTemplate;
    }

    public static Map getParamValueMap(Map paramValueMap){
        Map chartsMap = new HashMap();
        Iterator it = paramValueMap.keySet().iterator();
        while (it.hasNext()){
            String key = (String) it.next();
            Map paramDataMap = (Map)paramValueMap.get(key);
            Map paramChartMap = new HashMap();


            Map xAxis = new HashMap();
            xAxis.put("categories",paramDataMap.get("time"));
            paramChartMap.put("xAxis",xAxis);

            List seriesList = new ArrayList();
            Map hDataMap = new HashMap();
            hDataMap.put("name","最高值");
            hDataMap.put("data",paramDataMap.get("最高值"));
            seriesList.add(hDataMap);
            Map lDataMap = new HashMap();
            lDataMap.put("name","最低值");
            lDataMap.put("data",paramDataMap.get("最低值"));
            seriesList.add(lDataMap);
            Map aDataMap = new HashMap();
            aDataMap.put("name","均值");
            aDataMap.put("data",paramDataMap.get("均值"));
            seriesList.add(aDataMap);
            paramChartMap.put("series",seriesList);


            Map title = new HashMap();
            title.put("text",paramDataMap.get("pname")+" 历史指标");
            paramChartMap.put("title",title);

            Map chart = new HashMap();
            chart.put("type","line");
            paramChartMap.put("chart",chart);

            Map subtitle = new HashMap();
            subtitle.put("text","数据来源: Fishing1.0         (24小时全天侯监控)");
            paramChartMap.put("subtitle",subtitle);

            Map yAxis = new HashMap();
            Map titles = new HashMap();
            titles.put("text","值");
            yAxis.put("title",titles);
            paramChartMap.put("yAxis",yAxis);

            Map plotOptions = new HashMap();
            Map line = new HashMap();
            Map dataLabels = new HashMap();
            dataLabels.put("enabled",true);
            line.put("dataLabels",dataLabels);
            line.put("enableMouseTracking",false);
            plotOptions.put("line",line);
            paramChartMap.put("plotOptions",plotOptions);

            chartsMap.put(key,paramChartMap);

        }
        return chartsMap;
    }


    public static Map getForcastMap(Map forcastMap){
        Map forcastCharMap = new HashMap();

        Map xAxis = new HashMap();
        xAxis.put("categories",(List)forcastMap.get("time"));
        forcastCharMap.put("xAxis",xAxis);

        List seriesList = new ArrayList();
        Map phnameDataMap = new HashMap();
        phnameDataMap.put("name","PH");
        phnameDataMap.put("data",(List)forcastMap.get("ph"));
        seriesList.add(phnameDataMap);
        Map rynameDataMap = new HashMap();
        rynameDataMap.put("name","溶氧");
        rynameDataMap.put("data",(List)forcastMap.get("ry"));
        seriesList.add(rynameDataMap);
        forcastCharMap.put("series",seriesList);


        Map title = new HashMap();
        title.put("text",forcastMap.get("name"));
        forcastCharMap.put("title",title);

        Map chart = new HashMap();
        chart.put("type","line");
        forcastCharMap.put("chart",chart);

        Map subtitle = new HashMap();
        subtitle.put("text","数据来源: Fishing1.0         (24小时全天侯监控)");
        forcastCharMap.put("subtitle",subtitle);

        Map yAxis = new HashMap();
        Map titles = new HashMap();
        titles.put("text","值");
        yAxis.put("title",titles);
        forcastCharMap.put("yAxis",yAxis);

        Map plotOptions = new HashMap();
        Map line = new HashMap();
        Map dataLabels = new HashMap();
        dataLabels.put("enabled",true);
        line.put("dataLabels",dataLabels);
        line.put("enableMouseTracking",false);
        plotOptions.put("line",line);
        forcastCharMap.put("plotOptions",plotOptions);

        forcastCharMap.put("msg",forcastMap.get("msg"));

        return forcastCharMap;
    }


    public static Map getOneDeviceStaticCharMap(Map deviceMap) {
        String deviceName = (String) deviceMap.keySet().iterator().next();
        Map timeDataMap = (Map)deviceMap.get(deviceName);

        //初始化series
        List seriesList = new ArrayList();
        Set paramSet = ((Map)timeDataMap.get(timeDataMap.keySet().iterator().next())).keySet();
        Iterator it = paramSet.iterator();
        while(it.hasNext()){
            String pid = (String)it.next();
            Map nameDataMap = new HashMap();
            nameDataMap.put("name",pid.charAt(0)=='s'?pid.substring(1):pid);
            nameDataMap.put("data",new ArrayList());
            seriesList.add(nameDataMap);
        }
        //填充series中的数据集合
        Iterator it1 = timeDataMap.keySet().iterator();
        while (it1.hasNext()) {
            Map paramValueMap = (Map) timeDataMap.get(it1.next());
            Iterator it2 = paramValueMap.keySet().iterator();
            int i = 0;
            while (it2.hasNext()) {
                String pid = (String)it2.next();
                List dataList = (List) ((Map)seriesList.get(i)).get("data");
                Double value = (Double) paramValueMap.get(pid);
                dataList.add(value);
                i++;
            }
        }
        Map oneDeviceStaticCharMap = new HashMap();

        Map xAxis = new HashMap();
        xAxis.put("categories",timeDataMap.keySet().toArray());
        oneDeviceStaticCharMap.put("xAxis",xAxis);

        oneDeviceStaticCharMap.put("series",seriesList);

        Map title = new HashMap();
        title.put("text",deviceName);
        oneDeviceStaticCharMap.put("title",title);

        Map chart = new HashMap();
        chart.put("type","line");
        oneDeviceStaticCharMap.put("chart",chart);

        Map subtitle = new HashMap();
        subtitle.put("text","数据来源: Fishing1.0         (24小时全天侯监控)");
        oneDeviceStaticCharMap.put("subtitle",subtitle);

        Map yAxis = new HashMap();
        Map titles = new HashMap();
        titles.put("text","值");
        yAxis.put("title",titles);
        oneDeviceStaticCharMap.put("yAxis",yAxis);

        Map plotOptions = new HashMap();
        Map line = new HashMap();
        Map dataLabels = new HashMap();
        dataLabels.put("enabled",true);
        line.put("dataLabels",dataLabels);
        line.put("enableMouseTracking",false);
        plotOptions.put("line",line);
        oneDeviceStaticCharMap.put("plotOptions",plotOptions);

        return oneDeviceStaticCharMap;

    }
}
