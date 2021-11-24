package controller.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Chat;
import model.User;
import model.service.ChatManager;
import model.service.UserManager;

public class DeleteChatController implements Controller {
	 private static final Logger log = LoggerFactory.getLogger(DeleteChatController.class);

	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			String buddyId = request.getParameter("buddyId");
	    
			log.debug("buddyId : {}", buddyId);
			
			HttpSession session = request.getSession();	
			String userId =  UserSessionUtils.getLoginUserId(session);
			
	    	UserManager umanager = UserManager.getInstance();

	    	User user = null;
			user = umanager.findUser(userId);	
			
			log.debug("userId : {}", userId);

	    	ChatManager cmanager = ChatManager.getInstance();		
	    	cmanager.deleteRoom(userId, buddyId);
	    	
	    	List<Chat> chatList = cmanager.findChatContents(userId, buddyId);
	    	
	    	request.setAttribute("user", user);	
			request.setAttribute("chatList", chatList);			
			request.setAttribute("buddyId", buddyId);
			
	    	return "/chat/chatView.jsp";
		}
}
