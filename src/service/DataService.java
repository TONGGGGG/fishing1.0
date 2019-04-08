package service;


import java.util.ArrayList;
import java.util.List;

import bean.Data;
import bean.DataNode;
import correspondence.ReceiveData;
import dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DateUtil;

import javax.annotation.PostConstruct;


@Service
public class DataService {

    @Autowired
    DataDao dataDao;



    public List<DataNode> getDataNodeList(String param, String pid)throws Exception{
        List<DataNode> list = new ArrayList<DataNode>();
        //获取昨天0点到现在的所有data
        List<Data> dataList = dataDao.getAllData(pid
                ,String.valueOf(DateUtil.getTomorrowMills(System.currentTimeMillis()))
                ,String.valueOf(System.currentTimeMillis()));
        for (int i = 0; i < dataList.size(); i++) {
            String s = dataList.get(i).getData();
            int mils = DateUtil.getTodayMills(Long.parseLong(dataList.get(i).getTime()));
            Double time = Double.valueOf(mils);
            String[] str = s.split("\\|");
            for (int j = 0; j < str.length; j++) {
                if (str[j].substring(0,3).equals(param))
                    list.add(new DataNode(time,Double.valueOf(str[j].substring(3))));
            }
        }
        return list;
    }

//	public Data getRealTimeData(String device) {
//
//		//获取各项参值的上下限
//		Device d=new EquipDao().getDeviceByDevice(device);
//
//		//获取实时数据
//		Data data= new DataDao().getRealTimeDataByDevice(device);
//
//		String s="";
//
//		//对比各项参数
//		if(data.getParm1()<d.getP00()){
//			s+="水体温度低于正常值            ";
//			data.setAlert(true);
//			}
//		if(data.getParm1()>d.getP01()){
//			s+="<font size=\"3\" color=\"red\" face=\"Times\">水体温度低于正常值!</font>招财鱼属热带鱼类，对温度要求颇严，适宜水温22～32℃，在阴雨天或冷空气来临时，要及时加热水升温，平时要保持池水深度，升温时注意勿过急，以防温度骤变不利亲鱼生长。<br>"
//					+ "<font size=\"3\" color=\"green\" face=\"Times\">操作：已自动开启加热棒</font>";
//			data.setAlert(true);
//			}
//		if(data.getParm2()<d.getP10()){
//			s+="PH低于正常值            ";
//			data.setAlert(true);
//			}
//		if(data.getParm2()>d.getP11()){
//			s+="PH高于正常值            ";
//			data.setAlert(true);
//			}
//		if(data.getParm3()<d.getP20()){
//			s+="水体溶氧度低于正常值            ";
//			data.setAlert(true);
//			}
//		if(data.getParm3()>d.getP21()){
//			s+="水体溶氧度高于正常值            ";
//			data.setAlert(true);
//			}
//		if(data.getParm4()<d.getP30()){
//			s+="水体浑浊度低于正常值            ";
//			data.setAlert(true);
//			}
//		if(data.getParm4()>d.getP31()){
//			s+="水体浑浊度高于正常值            ";
//			data.setAlert(true);
//			}
//		if(data.getAlert()){
//	        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        String time = format0.format(data.getTime()*1000l);
//			data.setMsg(time+" : "+s);
//			}
//		return data;
//	}
//
//	public DataPackage getDataPackage(int time, String device) {
//		List<Data> list = new DataDao().getDataListByTime(time,device);
//
//		DataPackage dp = new DataPackage();
//
//		double[] p0 =new  double[list.size()];
//		double[] p1 =new  double[list.size()];
//		double[] p2 =new  double[list.size()];
//		double[] p3 =new  double[list.size()];
//		String[] t=new String[list.size()];
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for (int i=0;i<list.size();i++) {
//			Data data = list.get(i);
//			p0[i]=data.getParm1();
//			p1[i]=data.getParm2();
//			p2[i]=data.getParm3();
//			p3[i]=data.getParm4();
//			Date date = new Date(data.getTime()*1000l);
//			System.out.println(date);
//
//			t[i] = simpleDateFormat.format(date).substring(10);
//		}
//		dp.setP0(Arrays.toString(p0));
//		dp.setP1(Arrays.toString(p1));
//		dp.setP2(Arrays.toString(p2));
//		dp.setP3(Arrays.toString(p3));
//		dp.setTime(t);
//
//		long tt=time*1000l;
//		Date date = new Date(tt);
//		dp.setTitle("'"+simpleDateFormat.format(date).substring(0,10)+"水体监测'");
//
//		return dp;
//	}

}
