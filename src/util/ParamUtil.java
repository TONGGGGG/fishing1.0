package util;

import bean.Cre_param;
import bean.Device;
import bean.Param;
import bean.Pond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import service.DeviceService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

@Component
public class ParamUtil {
    static DeviceService deviceService;

    static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate ){
        ParamUtil.stringRedisTemplate=stringRedisTemplate;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService ){
        ParamUtil.deviceService=deviceService;
    }

    public static Double save2point(double f) {
        BigDecimal b   =   new   BigDecimal(f);
        return b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static String checkParamMap(Map paramMap, Pond pond){
        String mode = pond.getMode();
        StringBuffer msg = new StringBuffer();
        Iterator it = paramMap.keySet().iterator();
        while(it.hasNext()){
            String param = (String) it.next();
            Double value = (Double)paramMap.get(param);
            if (param.charAt(0)=='s'){
                if (mode!=null)
                    deviceService.turnOnSomeDevice(pond.getDevices(),param.substring(param.length()-3));
                if (msg.length()==0)
                    msg.append("Dear customer ，your pond:"+pond.getPname()+"'s context is unstable！/r/n");
                else
                    msg.append(param.substring(param.length()-3)+"::"+String.valueOf(value)+"/r/n");
            }

        }
        return msg.toString();
    }



    public static Map getCre_paramMap(Set<Cre_param> cre_paramSet){
        Map cre_paramMap = new HashMap();
        Iterator it = cre_paramSet.iterator();
        while(it.hasNext()){
            Cre_param cre_param = (Cre_param) it.next();
            if (cre_param.getType().equals("1"))
                cre_paramMap.put("L"+cre_param.getParam().getPid(),cre_param.getValue());
            else
                cre_paramMap.put("H"+cre_param.getParam().getPid(),cre_param.getValue());
        }
        return cre_paramMap;
    }

    public static Map getParamMap(Map cre_paramMap,Map pidMap){
        Map paramMap = new LinkedHashMap();
        Iterator it = pidMap.keySet().iterator();
        while(it.hasNext()){
            String pid = (String) it.next();
            Double value = Double.valueOf((String)pidMap.get(pid));
            if ((Double.valueOf((String)cre_paramMap.get("H"+pid.substring(pid.length()-3)) ) <value)||(Double.valueOf((String)cre_paramMap.get("L"+pid.substring(pid.length()-3)))>value)  )
                paramMap.put("s"+pid,value);
            else
                paramMap.put(pid,value);
        }
        return paramMap;
    }

    public static Map analysisMap(String name,Map cre_paramMap,Map pidMap){
        if (pidMap.get("001")==null||pidMap.get("004")==null){
            Map map = new HashMap();
            map.put("average_data",getParamMap(cre_paramMap,pidMap));
            return map;
        }
        Map map = new HashMap();
        Double phValue = Double.valueOf((String)pidMap.get("001"));
        Double ryValue = Double.valueOf((String)pidMap.get("004"));
        Double h_ph = Double.valueOf((String)cre_paramMap.get("H001"));
        Double l_ph = Double.valueOf((String)cre_paramMap.get("L001"));
        Double h_ry = Double.valueOf((String)cre_paramMap.get("H004"));
        Double l_ry = Double.valueOf((String)cre_paramMap.get("L004"));
        int score = 100;
        String advice = "";
        if (h_ph<phValue){
            score-=((phValue-h_ph)/0.1)*5;
            advice+="当前生物:"+name+"的养殖pH值的适宜范围为"+l_ph+"~"+h_ph+"当前已偏离最高值,原因可能为：藻类过度生长繁殖，大量消耗水中碳源（二氧化碳），致使水体PH值快速上升（光合细菌过度生长繁殖也会造成PH值上升）。PH值偏高，水体中铵氮以氨分子氮形式存在，增加了氨氮的毒性；另外，高PH值水质对鳃部组织有腐蚀作用。。pH值太高使蛋白质发生玻璃样变性，鳃组织失去呼吸功能。养殖水体的pH值对药物和毒物的作用均有影响；";
        }
        if (l_ph>phValue){
            score-=((l_ph-phValue)/0.1)*5;
            advice+="当前生物:"+name+"的养殖pH值的适宜范围为"+l_ph+"~"+h_ph+"当前已偏离最低值,原因可能为：水体缺氧，水体有机质过多，在厌氧菌厌氧发酵的作用下，产生大量有机酸，致使水体PH值偏低。PH值偏低，致病菌容易大量繁殖，且硫化氢毒性增强；";
        }
        if (l_ry>ryValue){
            score-=((l_ry-ryValue)/0.1)*10;
            advice+="当前生物:"+name+"的养殖溶氧不应低于"+l_ph+"当前已偏离最低值，溶解氧过低会引发养殖鱼类的窒息死亡，俗称“泛池”。水中有机物、氨氮等厌氧分解，产生亚硝酸盐等有毒物质；容易滋生细菌，造成养殖生动物疾病频发或大量死亡；引起水生动物严重贫血、生长缓慢、背部体色变淡、唇肥大等；低氧状态下水生动物呼吸受阻，鱼虾只进食但不生长，消耗自身的能量；溶氧低于其最低限度时就会引起窒息死亡；";
        }
        if (h_ry<ryValue){
            score-=((ryValue-h_ry)/0.1)*5;
            advice+="当前生物:"+name+"的养殖溶氧不宜高于"+h_ry+"当前已偏离最高值，过高溶解氧会引发气泡病，气泡病对成鱼的影响不大，而一般会对鱼苗产生危害。溶解过饱和的原因有：浮游植物过多，增氧措施不当，水温突然大幅升高等因素；";
        }

        map.put("average_data",ParamUtil.cNParamMap(getParamMap(cre_paramMap,pidMap)));
        map.put("score",score<0?0:score);
        map.put("advice",score==100?"水质良好！请继续保持哦~":advice);
        return map;
    }

    public static Map cNParamMap(Map paramMap){
        Map cNParamMap = new HashMap();
        Iterator it = paramMap.keySet().iterator();
        while (it.hasNext()){
            String key = (String) it.next();
            String paramId = key.substring(key.length()-3);
            String cnName = (String) stringRedisTemplate.opsForHash().get("paramMap",paramId);
            cNParamMap.put(key.replace(paramId,cnName),paramMap.get(key));
        }
        return cNParamMap;
    }


    public static String getParamName(String s){
        switch (s){
            case "001":
                s = "温度";
                break;
            case "002":
                s = "PH";
                break;
            case "003":
                s = "浑浊度";
                break;
            case "004":
                s = "溶氧度";
                break;
            case "005":
                s = "温度";
                break;
            case "006":
                s = "温度";
                break;
            case "007":
                s = "温度";
                break;
            default :
                s = "未知指标";
        }
        return s;
    }
}
