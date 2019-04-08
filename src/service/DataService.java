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
        //��ȡ����0�㵽���ڵ�����data
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
//		//��ȡ�����ֵ��������
//		Device d=new EquipDao().getDeviceByDevice(device);
//
//		//��ȡʵʱ����
//		Data data= new DataDao().getRealTimeDataByDevice(device);
//
//		String s="";
//
//		//�Աȸ������
//		if(data.getParm1()<d.getP00()){
//			s+="ˮ���¶ȵ�������ֵ            ";
//			data.setAlert(true);
//			}
//		if(data.getParm1()>d.getP01()){
//			s+="<font size=\"3\" color=\"red\" face=\"Times\">ˮ���¶ȵ�������ֵ!</font>�в������ȴ����࣬���¶�Ҫ�����ϣ�����ˮ��22��32�棬������������������ʱ��Ҫ��ʱ����ˮ���£�ƽʱҪ���ֳ�ˮ��ȣ�����ʱע����������Է��¶���䲻������������<br>"
//					+ "<font size=\"3\" color=\"green\" face=\"Times\">���������Զ��������Ȱ�</font>";
//			data.setAlert(true);
//			}
//		if(data.getParm2()<d.getP10()){
//			s+="PH��������ֵ            ";
//			data.setAlert(true);
//			}
//		if(data.getParm2()>d.getP11()){
//			s+="PH��������ֵ            ";
//			data.setAlert(true);
//			}
//		if(data.getParm3()<d.getP20()){
//			s+="ˮ�������ȵ�������ֵ            ";
//			data.setAlert(true);
//			}
//		if(data.getParm3()>d.getP21()){
//			s+="ˮ�������ȸ�������ֵ            ";
//			data.setAlert(true);
//			}
//		if(data.getParm4()<d.getP30()){
//			s+="ˮ����Ƕȵ�������ֵ            ";
//			data.setAlert(true);
//			}
//		if(data.getParm4()>d.getP31()){
//			s+="ˮ����Ƕȸ�������ֵ            ";
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
//		dp.setTitle("'"+simpleDateFormat.format(date).substring(0,10)+"ˮ����'");
//
//		return dp;
//	}

}
