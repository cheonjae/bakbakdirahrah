package controller.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ChatManager;

public class ListChatController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ChatManager manager = ChatManager.getInstance();
    	String userId = request.getParameter("userId");

    	List<String> buddyList = manager.findBuddyId(userId);
    	for (String id : buddyList) {
    		if (id.equals(userId)) {
    			buddyList.remove(id);
    		}
    	}
			
    	request.setAttribute("user", userId);
		request.setAttribute("buddyList", buddyList);
		return "/chat/chatList.jsp"; 
    }
}