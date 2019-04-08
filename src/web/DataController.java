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
//		//获取设备id
//		String device = request.getParameter("eid");
//
//		//获取data对象
//		Data d = new DataService().getRealTimeData(device);
//
//		//转化为json格式
//		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
//		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
//		jsonConfig.setExcludes(new String[]{"device"});
//		JSONObject jsonObject = JSONObject.fromObject(d,jsonConfig);
//
//
//
//		//3.写回去
//		response.setContentType("text/json;charset=UTF-8");
//		response.getWriter().println(jsonObject.toString());
//		System.out.println(jsonObject.toString());
//
//	}
	
//	public String getDataPackage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
//		// TODO Auto-generated method stub
//		//获取设备id
//		String device = request.getParameter("device");
//
//		//获取时间戳
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse(request.getParameter("time")+" 00:00:00");
//				System.out.println((int)(date.getTime()/1000)+" "+device);
//		//获取该天的DataPackage
//		DataPackage dp = new DataService().getDataPackage((int)(date.getTime()/1000),device);
//
//		request.setAttribute("dp", dp);
//
//		return "history.jsp";
//	}
}
