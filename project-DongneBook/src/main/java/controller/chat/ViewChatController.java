package controller.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ChatManager;
import model.service.UserManager;
import model.Chat;
import model.User;

public class ViewChatController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ChatManager manager = ChatManager.getInstance();
    	String userId = request.getParameter("userId");
    	String buddyId = request.getParameter("buddyId");

    	List<Chat> chatList = manager.findChatContents(userId, buddyId);
    	
    	UserManager umanager = UserManager.getInstance();
    	
    	User user = null;
		user = umanager.findUser(userId);	
		
    	request.setAttribute("user", user);	
		request.setAttribute("chatList", chatList);			
		request.setAttribute("buddyId", buddyId);
		return "/chat/chatView.jsp"; 
    }
}
