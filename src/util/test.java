package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class test {

    public static void main(String[] args) {
        String s = "";
        HashMap map = new HashMap();
        Iterator it1 = map.keySet().iterator();
        while (it1.hasNext()){
            Object pondkey = it1.next();//鱼塘名
            Map deviceMap = (Map) map.get(pondkey);
            Iterator it2 = deviceMap.keySet().iterator();
            while (it2.hasNext()){
                Object devicekey = it2.next();//设备名
                s+=devicekey+"\n";
                Map dateMap = (Map) map.get(devicekey);
                Iterator it3 = dateMap.keySet().iterator();
                while (it3.hasNext()){
                    Object datekey = it3.next();//日期名
                    Map paramMap = (Map) map.get(datekey);
                    Iterator it4 = paramMap.keySet().iterator();

                    while (it4.hasNext()){

                        Object paramkey = it4.next();//参数名
                        Object value = paramMap.get(paramkey);
                        s+=paramkey+":"+value+"  ";
                    }
                }
                s+="\n";
            }
        }
    }


}
