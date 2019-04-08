package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/showlogin")
	public String showLogin(){
		return "login";
	}

	@RequestMapping(value = "/showregister")
	public String showregister(){
		return "register";
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		User u=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.and(Restrictions.eq("email", request.getParameter("email")), Restrictions.eq("passwd", request.getParameter("passwd"))));
		u=userService.login(criteria);
		if(u!=null){
			request.getSession().setAttribute("user", u);

			if(request.getParameter("app")!=null){
				response.getWriter().append("1");
				return null;
			}

			return "home";
		}
		else{
			request.setAttribute("msg", "用户名或密码错误");
			return "login";
		}
	}

	@RequestMapping(value = "/emailCheck")
	public String emailCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		BeanUtils.populate(user, map);
		int s = userService.emailCheck(user, request);
		if("1".equals(request.getParameter("app"))){
			response.getWriter().append(String.valueOf(s));
			if (s==1)
				request.getSession().setAttribute("user",user);
			return null;
		}
		if (s==1){
			request.getSession().setAttribute("user",user);
			response.getWriter().append("{\"state\":\"1\"}");
			return null;
		}
		response.getWriter().append("\"state:0\"");
		return null;
}
	@RequestMapping(value = "/regist")
	public  String regist(HttpServletRequest request, HttpServletResponse response)throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		int s = userService.identifyCode(request);
		if(s==2||s==0){
			if(request.getParameter("app")!=null){
				response.getWriter().append(String.valueOf(s));
				return null;
			}

			response.getWriter().append("{\"state\":\""+s+"\"}");
			return null;
		}
		s = userService.rigist((User)request.getSession().getAttribute("user"));
		if(request.getParameter("app")!=null){
			response.getWriter().append(String.valueOf(s));
			return null;
		}
		response.getWriter().append("{\"state\":\""+s+"\"}");
		return null;
	}


	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException, InvocationTargetException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("login.jsp");
		return null;
}
	}
