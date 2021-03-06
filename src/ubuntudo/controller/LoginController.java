package ubuntudo.controller;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.dao.UserDao;
import ubuntudo.model.AjaxRedirectResponse;
import ubuntudo.model.UserEntity;
import core.utils.RSAUtils;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	String loginSuccessedViewUri = "jsp/personal.jsp";
	String loginFailedViewUri = "jsp/loginFail.jsp";

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody AjaxRedirectResponse execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("-->Controller-->Login");
		
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		
		HttpSession session = request.getSession();    
		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSAWebKey"); 
		
		String _email = RSAUtils.decryptRsa(privateKey, email);
        String _password = RSAUtils.decryptRsa(privateKey, passwd);
		logger.debug("Logging in email: {}", _email);
		logger.debug("Logging in passwd: {}", _password);

		UserDao uDao = new UserDao();
		
		UserEntity currentUser = uDao.retrieveUser(_email, _password);

		AjaxRedirectResponse res = new AjaxRedirectResponse();
		if (currentUser != null) {
			// member login success
			session.setAttribute("user", currentUser);
			logger.info((session.getAttribute("user").toString()));
			res.setStatus("success");
			res.setUri(loginSuccessedViewUri);
			
		} else {
			res.setStatus("fail");
			res.setUri(loginFailedViewUri);
		}
		logger.info("<--Controller-->Login");
		return res;
	}

}
