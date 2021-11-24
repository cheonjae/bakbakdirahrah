package 
controller.chat

;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.ChatManager;

public class ListChatController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(ListChatController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ChatManager manager = ChatManager.getInstance();
    	String userId = request.getParameter("userId");

    	List<String> buddyList = manager.findBuddyId(UserSessionUtils.getLoginUserId(request.getSession()));
    	for (String id : buddyList) {
    		if (id.equals(userId)) {
    			buddyList.remove(id);
    		}
    	}
    	buddyList = buddyList.stream().distinct().collect(Collectors.toList());
			
		request.setAttribute("buddyList", buddyList);
		return "/chat/chatList.jsp"; 
    }
} 
