package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.book.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 占쏙옙 占쏙옙청 uri占쏙옙 占쏙옙占쏙옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 HashMap 占쏙옙占쏙옙
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
//        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
     
        
        // 회占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙청占쏙옙 占쏙옙占쏙옙 占쏙옙청 처占쏙옙 占쏙옙占쏙옙 (占쏙옙占쏙옙 커占승댐옙티 占쏙옙占쏙옙 占쌨댐옙 占쌩곤옙占쏙옙 占쏙옙占쏙옙)
      mappings.put("/book/register/form", new ForwardController("/book/bookRegisterForm.jsp"));
      mappings.put("/book/register", new RegisterBookController());
//        mappings.put("/user/register", new RegisterUserController());

        // 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙청占쏙옙 占쏙옙占쏙옙 占쏙옙청 처占쏙옙 占쏙옙占쏙옙
//      mappings.put("/user/update/form", new UpdateUserFormController());
//      mappings.put("/user/update", new UpdateUserController());        
//        mappings.put("/user/update", new UpdateUserController());
        
        mappings.put("/user/main", new MainBookListController());
        
        mappings.put("/book/delete", new DeleteBookController());
        mappings.put("/book/detail", new BookDetailController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 占쌍억옙占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 찾占쏙옙 占쏙옙환
        return mappings.get(uri);
    }
}
