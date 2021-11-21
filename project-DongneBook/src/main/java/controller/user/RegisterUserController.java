package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {	
	
    		log.debug("RegisterForm Request");
			return "/user/userRegisterForm.jsp";   	
	    }	

    	// POST request
       	
       	String userId = request.getParameter("userId");
       	String password = request.getParameter("password");
       	User user = new User(
			userId, password, 
			request.getParameter("name"),
			request.getParameter("email"),
			request.getParameter("phone"),
			request.getParameter("location"));
		
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			manager.login(userId, password); // 로그인된 채로 메인화면으로 가기 위해 
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
           
	        return "redirect:/user/main";
	        
		} catch (ExistingUserException e) {	
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/userRegisterForm.jsp";
		}
    }
}
