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
			// 紐⑤뜽�뿉 濡쒓렇�씤 泥섎━瑜� �쐞�엫
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
	
			// �꽭�뀡�뿉 �궗�슜�옄 �씠�씠�뵒 ���옣
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/main";			
		} catch (Exception e) {
			/* UserNotFoundException�씠�굹 PasswordMismatchException 諛쒖깮 �떆
			 * �떎�떆 login form�쓣 �궗�슜�옄�뿉寃� �쟾�넚�븯怨� �삤瑜� 硫붿꽭吏��룄 異쒕젰
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			request.setCharacterEncoding("UTF-8");
            return "/user/loginForm.jsp";			
		}	
    }
}
