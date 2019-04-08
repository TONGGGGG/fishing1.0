package web;

import bean.DataNode;
import bean.Pond;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import forecast.LinearRegression;
import inteltask.IntelTask;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DataService;
import service.LinearRegressionService;
import service.PondService;
import statistic.PondStatistic;
import util.DateUtil;
import util.HcharUtil;
import util.JsonUtil;
import util.ParamUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/pond")
public class PondController {

    @Autowired
    PondService pondService;

    @Autowired
    DataService dataService;

    @Autowired
    IntelTask intelTask;

    @Autowired
    PondStatistic pondStatistic;

    @Autowired
    LinearRegressionService linearRegressionService;

//    @RequestMapping("/index")
//    public String index(HttpServletRequest request , HttpServletResponse response){
//
//    }

    @RequestMapping(value = "/history_detail")
    public String history_detail(){
        return "history_detail";
    }

    @RequestMapping(value = "/history_index")
    public String history_index(){
        return "history_index";
    }

    @RequestMapping(value = "/pond_forcast")
    public String pond_forcast(){
        return "pond_forcast";
    }




    //当日参数预测
    @RequestMapping("/forecast")
    public String forecast(HttpServletRequest request , HttpServletResponse response)throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            request.setAttribute("msg","您还未登陆，请登陆后操作！");
            return "msg.jsp";
        }
        Pond pond = pondService.getPond(request.getParameter("pid"));
        List<DataNode> phDataNodeList = dataService.getDataNodeList("001",pond.getPid());
        List<DataNode> ryDataNodeList = dataService.getDataNodeList("004",pond.getPid());

        double[] ryABR = linearRegressionService.getABR(ryDataNodeList);
        double[] phABR = linearRegressionService.getABR(phDataNodeList);

        Set cre_paramSet = pondService.getPond(request.getParameter("pid")).getCreature().getCre_params();
        Map cre_paramMap = ParamUtil.getCre_paramMap(cre_paramSet);
        Map map = JsonUtil.forecastMap(ryABR,phABR,cre_paramMap);
        map.put("name",pond.getPname()+DateUtil.getNowDate(System.currentTimeMillis()).substring(0,10)+"预测");
        if (request.getParameter("app")!=null){
            response.getWriter().append(JsonUtil.map2json(map));
            return null;
        }
        response.getWriter().append(JSONObject.toJSONString(HcharUtil.getForcastMap(map)));
        return null;
    }
    //池塘列表
    @RequestMapping("/pondList")
    public String index(HttpServletRequest request , HttpServletResponse response)throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            if(request.getParameter("app")!=null){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().append("-1");
                return null;
            }
            request.setAttribute("msg","您还未登陆，请登陆后操作！");
            return "login";
        }
        Map map = pondService.getAllPondMap(user);
        if (request.getParameter("app")!=null){
            response.getWriter().append(JsonUtil.map2json(map));
            return null;
        }
        response.getWriter().append(JsonUtil.map2json(map));
        return null;
    }




    //池塘详情
    @RequestMapping("/pondDetail")
    public String pondDetail(HttpServletRequest request , HttpServletResponse response)throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            request.setAttribute("msg","您还未登陆，请登陆后操作！");
            return "msg.jsp";
        }
        Map index = pondService.getPondMap(request.getParameter("pid"),request.getParameter("date"));
        if (request.getParameter("app")!=null){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().append(JsonUtil.map2json(index));
            return null;
        }
        return null;
    }



    //池塘所有设备的char（一段时间内所有值）
    @RequestMapping("/pondAllDeviceChar")
    public String pondAllDeviceChar(HttpServletRequest request , HttpServletResponse response)throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            request.setAttribute("msg","您还未登陆，请登陆后操作！");
            return "msg.jsp";
        }
        if (request.getParameter("app")!=null){
            Map map = pondService.getPondAllDeviceMap(request.getParameter("pid"),request.getParameter("beginTime"),request.getParameter("endTime"),false);
            response.getWriter().append(JSONObject.toJSONString(map));
            return null;
        }
        Map map = pondService.getPondAllDeviceMap(request.getParameter("pid"),request.getParameter("beginTime"),request.getParameter("endTime"),true);
        response.getWriter().append(JSONObject.toJSONString(map));
        return null;
    }


    @RequestMapping("/pondIndex")
    public String pondIndex(HttpServletRequest request, HttpServletResponse response)throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("msg", "您还未登陆，请登陆后操作！");
            return "msg.jsp";
        }
        if (request.getParameter("app") != null) {
            Map map = pondStatistic.getPondIndex(request.getParameter("pid"),request.getParameter("beginTime"),request.getParameter("endTime"),false);
            response.getWriter().append(JSONObject.toJSONString(map));
            return null;
        }
        Map map = pondStatistic.getPondIndex(request.getParameter("pid"),request.getParameter("beginTime"),request.getParameter("endTime"),true);
        response.getWriter().append(JSONObject.toJSONString(map));
        return null;
    }




    //新增池塘
    @RequestMapping("/addPond")
    public String addPond(HttpServletRequest request , HttpServletResponse response)throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            request.setAttribute("msg","您还未登陆，请登陆后操作！");
            return "msg.jsp";
        }
        Pond pond = new Pond();
        try {
            BeanUtils.populate(pond,request.getParameterMap());
            pond.setUser(user);
            pondService.addPond(pond);
        }catch (Exception e){
            if (request.getParameter("app")!=null){
                response.getWriter().append("0");
                return null;
            }
            request.setAttribute("msg","添加失败！");
            return "msg.jsp";
        }

        if (request.getParameter("app")!=null){
            response.getWriter().append("1");
            return null;
        }

        request.setAttribute("msg","添加成功！");
        return "msg.jsp";

    }

}
