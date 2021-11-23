package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.book.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �뜝�룞�삕 �뜝�룞�삕泥� uri�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 controller �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 HashMap �뜝�룞�삕�뜝�룞�삕
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// �뜝�룞�삕 uri�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떎�뙋�삕 controller �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
//        mappings.put("/", new ForwardController("index.jsp"));
    	mappings.put("/user/main", new MainBookListController());
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/register/form", new ForwardController("/user/userRegisterForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
     
        // �쉶�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕泥��뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕泥� 泥섇뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 (�뜝�룞�삕�뜝�룞�삕 而ㅵ뜝�듅�뙋�삕�떚 �뜝�룞�삕�뜝�룞�삕 �뜝�뙣�뙋�삕 �뜝�뙥怨ㅼ삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕)
        mappings.put("/book/register/form", new ForwardController("/book/bookRegisterForm.jsp"));
        mappings.put("/book/register", new RegisterBookController());
        mappings.put("/book/delete", new DeleteBookController());
        mappings.put("/book/detail", new BookDetailController());
        mappings.put("/book/search", new SearchBookListController());
        mappings.put("/book/cate", new CateBookListController());
        mappings.put("/book/update", new UpdateBookController()); 
        
        mappings.put("/chat/view", new ViewChatController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// �뜝�뙇�뼲�삕�뜝�룞�삕 uri�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떎�뙋�삕 controller �뜝�룞�삕泥닷뜝�룞�삕 李얍뜝�룞�삕 �뜝�룞�삕�솚
        return mappings.get(uri);
    }
}
