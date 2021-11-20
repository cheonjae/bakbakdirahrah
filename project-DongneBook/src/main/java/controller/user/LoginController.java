package controller.user;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.PasswordMismatchException;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try {
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
	
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/main";			
		} catch (Exception e) {
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			request.setCharacterEncoding("UTF-8");
            return "/user/loginForm.jsp";			
		}	
    }
}
