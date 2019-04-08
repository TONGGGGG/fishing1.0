package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Data;
import bean.DataPackage;
import correspondence.ReceiveData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DataService;

/**
 * Servlet implementation class DataServlet
 */
@RequestMapping("/data")
@Controller
public class DataController  {

    @Autowired
    ReceiveData receiveData;

	@RequestMapping("/startService")
    public void startService()throws Exception{
	    receiveData.receiveService();
    }

//	public void getRealTimeData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//��ȡ�豸id
//		String device = request.getParameter("eid");
//
//		//��ȡdata����
//		Data d = new DataService().getRealTimeData(device);
//
//		//ת��Ϊjson��ʽ
//		JsonConfig jsonConfig = new JsonConfig();  //���������ļ�
//		jsonConfig.setIgnoreDefaultExcludes(false);  //����Ĭ�Ϻ���
//		jsonConfig.setExcludes(new String[]{"device"});
//		JSONObject jsonObject = JSONObject.fromObject(d,jsonConfig);
//
//
//
//		//3.д��ȥ
//		response.setContentType("text/json;charset=UTF-8");
//		response.getWriter().println(jsonObject.toString());
//		System.out.println(jsonObject.toString());
//
//	}
	
//	public String getDataPackage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
//		// TODO Auto-generated method stub
//		//��ȡ�豸id
//		String device = request.getParameter("device");
//
//		//��ȡʱ���
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse(request.getParameter("time")+" 00:00:00");
//				System.out.println((int)(date.getTime()/1000)+" "+device);
//		//��ȡ�����DataPackage
//		DataPackage dp = new DataService().getDataPackage((int)(date.getTime()/1000),device);
//
//		request.setAttribute("dp", dp);
//
//		return "history.jsp";
//	}
}
