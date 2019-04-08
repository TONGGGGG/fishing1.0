package service;

import bean.Param;
import dao.ParamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParamService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ParamDao paramDao;

    @PostConstruct
    public void initParamMap(){
        List<Param> paramList = paramDao.getParamList();
        Map paramMap = new HashMap();
        for (Param param:paramList) {
            paramMap.put(param.getPid(),param.getPname());
        }
        stringRedisTemplate.opsForHash().putAll("paramMap",paramMap);
    }
}
