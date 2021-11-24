package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.book.*;
import controller.chat.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	mappings.put("/user/main", new MainBookListController());
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/register/form", new ForwardController("/user/userRegisterForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
     
        mappings.put("/book/register/form", new ForwardController("/book/bookRegisterForm.jsp"));
        mappings.put("/book/register", new RegisterBookController());
        mappings.put("/book/delete", new DeleteBookController());
        mappings.put("/book/detail", new BookDetailController());
        mappings.put("/book/search", new SearchBookListController());
        mappings.put("/book/cate", new CateBookListController());
        mappings.put("/book/update", new UpdateBookController()); 
        
        //채팅
        mappings.put("/chat/view", new ViewChatController());
        mappings.put("/chat/list", new ListChatController());
        mappings.put("/chat/roomDelete", new DeleteChatController());
        mappings.put("/chat/create", new CreateChatController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	return mappings.get(uri);
    }
}
