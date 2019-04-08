package service;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.criterion.DetachedCriteria;

import bean.User;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.EmailHelper;
import util.UUIDUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public User login(DetachedCriteria criteria) throws SQLException {
		return userDao.login(criteria);
	}

	public int emailCheck(User user,HttpServletRequest request) {
		String email = user.getEmail();
		if(userDao.checkEmail(email) == 1){
			return 0;
		}
		String checkCode = UUIDUtils.getCode().substring(0,5);

		EmailHelper.sendEmail(user.getEmail(),"Intelligent Cultivation",checkCode);

		//把验证时间放在session里
		request.getSession().setAttribute("emailTimeFlag", new Date().getTime());
		request.getSession().setAttribute("emailCodeFlag", checkCode);
		//把验证码放在session里  md5 2次加密验证码
		String codeMd5 = DigestUtils.md5Hex((DigestUtils.md5Hex(checkCode).toLowerCase()));
		request.getSession().setAttribute("emailCodeFlag", codeMd5);

		return 1;
	}

	public int identifyCode(HttpServletRequest request){
		//验证码有效期校验
		String email_validity = "1000000";
		Object emailTimeFlag = request.getSession().getAttribute("emailTimeFlag");
		if (emailTimeFlag != null) {
			int miao = (int) (((new Date()).getTime() - (Long) emailTimeFlag) / 1000);
			if (miao >= Integer.parseInt(email_validity)) {
				return 2;
			}else{
				//在有效期内 比对验证码准确性
				String checkcode = request.getParameter("checkCode");
				// md5 2次加密验证码
				String codeMd5 = DigestUtils.md5Hex((DigestUtils.md5Hex(checkcode).toLowerCase()));
				Object emailCodeFlag = request.getSession().getAttribute("emailCodeFlag");
				boolean res = codeMd5.equals(String.valueOf(emailCodeFlag));
				if(res){
					return 1;
				}else{
					return 0;
				}
			}
		}
		return 0;
	}

	public int rigist(User user){
			try {
				userDao.regist(user);
			}catch (Exception e){
				return 0;
			}
			return 1;
	}
	
}
