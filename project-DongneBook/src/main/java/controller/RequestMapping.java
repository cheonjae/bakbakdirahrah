package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.book.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {

//        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
     
        

      mappings.put("/book/register/form", new ForwardController("/book/bookRegisterForm.jsp"));
      mappings.put("/book/register", new RegisterBookController());

        mappings.put("/book/update", new UpdateBookController()); 
        
        mappings.put("/user/main", new MainBookListController());
        
        mappings.put("/book/delete", new DeleteBookController());
        mappings.put("/book/detail", new BookDetailController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	

        return mappings.get(uri);
    }
}
