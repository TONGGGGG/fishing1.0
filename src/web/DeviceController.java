package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import bean.Device;
import bean.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DeviceService;
import util.JsonUtil;

/**
 * Servlet implementation class EquipServlet
 */
@Controller
@RequestMapping("device")
public class DeviceController {

	@Autowired
    DeviceService deviceService;

	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/history_index")
	public String history_index(){
		return "history_index";
	}

	@RequestMapping("/allDeviceIndex")
	public String allDeviceIndex(HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "您还未登陆，请登陆后操作！");
			return "msg.jsp";
		}
		if (request.getParameter("app") != null) {
			Map map = deviceService.getPondAllDeviceHLMap(request.getParameter("pid"), request.getParameter("beginTime"), request.getParameter("endTime"),false);
			response.getWriter().append(JSONObject.toJSONString(map));
			return null;
		}
		Map map = deviceService.getPondAllDeviceHLMap(request.getParameter("pid"), request.getParameter("beginTime"), request.getParameter("endTime"),true);
		response.getWriter().append(JSONObject.toJSONString(map));
		return null;
	}



	@RequestMapping("changeState")
	public void changeState(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String did= request.getParameter("device");
		Device device =new DeviceService().getDevice(did);
		device.setState(request.getParameter("state"));
		deviceService.changeState(device);
	}



//	@RequestMapping("changeMode")
//	public void changeMode(HttpServletRequest request, HttpServletResponse response)throws Exception{
//		String did= request.getParameter("device");
//		Device device =new DeviceService().getDevice(did);
//		device.setMode(request.getParameter("mode"));
////		equipService.changeMode(device);
//	}


	
	@RequestMapping("/getDevice")
	public String getDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//获取device
		String did= request.getParameter("did");
		
		//向下传递返回device对象
		Device d=new DeviceService().getDevice(did);
		
		//转发
		request.setAttribute("d", d);
		
		return "edit.jsp";
	}
	public String registDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		response.setCharacterEncoding("UTF-8");;
		
		//获取user对象
		HttpSession session = request.getSession();
		
		Object object = session.getAttribute("user");
		
		if(object==null){
			response.getWriter().println("请先登陆！");
			return null;
		}
		
		User user=(User) object;

		//校验身份信息，密码是否正确
		if(!user.getPasswd().equals(request.getParameter("password"))){
			response.getWriter().println("密码不正确");
			return null;
		}
		
		//将参数封装到device对象中
		Device device = new Device();
		Map<String, String[]> map = request.getParameterMap();
		BeanUtils.populate(device, map);
//		device.setUser(user);
		
		//向下传递
		String msg=new DeviceService().registDevice(device);
		
		response.getWriter().println(msg);
		return null;
	}
	public String upDateDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		response.setCharacterEncoding("UTF-8");;
		
		
		//获取user对象
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		
		if(object==null){
			request.setAttribute("msg", "您还未登陆！");
			
			return "msg.jsp";
		}
		
		User user=(User) object;

		//校验身份信息，密码是否正确
		if(!user.getPasswd().equals(request.getParameter("password"))){
			response.getWriter().println("密码不正确");
			return null;
		}
		
		//将参数封装到device对象中
		Device device = new Device();
		Map<String, String[]> map = request.getParameterMap();
		BeanUtils.populate(device, map);
//		device.setUser(user);
		
//		向下传递
		String msg = new DeviceService().upDateDevice(device);
		
		response.getWriter().println(msg);
		return null;
	}
	public String deletDevice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		//获取user对象
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		
		if(object==null){
			request.setAttribute("msg", "您还未登陆！");
			
			return "msg.jsp";
		}
		

//		向下传递
		String msg = new DeviceService().DeletDevice(request.getParameter("device"));
		
		request.setAttribute("msg", msg);
		
		return "msg.jsp";
	}	
	
}
