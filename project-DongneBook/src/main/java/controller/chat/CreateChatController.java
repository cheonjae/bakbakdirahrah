package controller.chat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.ChatManager;
import model.service.UserManager;
import model.Chat;
import model.User;

public class CreateChatController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateChatController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// POST request
    		
//		HttpSession session = request.getSession();	
//		String userId =  UserSessionUtils.getLoginUserId(session);
    	String userId = request.getParameter("userId");
		log.debug("userId : {}", userId);

    	String buddyId = request.getParameter("buddyId");
    	log.debug("buddyId : {}", buddyId);
    	
       	Chat chat = new Chat(
			"0",
			request.getParameter("contents"),
			0,
			request.getParameter("userId"),
			request.getParameter("buddyId"));
    	
        log.debug("Create Chat : {} {} -> {}", chat.getContents(), chat.getSenderId(), chat.getReceiverId());

		try {
			ChatManager manager = ChatManager.getInstance();
			manager.create(chat);
			manager.findChatContents(userId, buddyId);
           
	    	List<Chat> chatList = manager.findChatContents(userId, buddyId);

	    	UserManager umanager = UserManager.getInstance();

	    	User user = null;
			user = umanager.findUser(userId);	

	    	request.setAttribute("user", user);	
			request.setAttribute("chatList", chatList);			
			request.setAttribute("buddyId", buddyId);
			
			return "/chat/chatView.jsp"; 
			//return "redirect:/chat/view";
	        
		} catch (Exception e) {	
            request.setAttribute("chatCreateFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("chat", chat);
			return "redirect:/user/main";
		}
	}

}
