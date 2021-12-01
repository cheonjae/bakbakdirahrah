package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.book.*;
import controller.chat.*;
import controller.transaction.*;
import controller.wishlist.*;

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
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/view", new ViewUserController());
        
        mappings.put("/book/register/form", new ForwardController("/book/bookRegisterForm.jsp"));
        mappings.put("/book/register", new RegisterBookController());
        mappings.put("/book/delete", new DeleteBookController());
        mappings.put("/book/detail", new BookDetailController());
        mappings.put("/book/search", new SearchBookListController());
        mappings.put("/book/cate", new CateBookListController());
        mappings.put("/book/update", new UpdateBookController()); 
        
        mappings.put("/chat/view", new ViewChatController());
        mappings.put("/chat/list", new ListChatController());
        mappings.put("/chat/roomDelete", new DeleteChatController());
        mappings.put("/chat/create", new CreateChatController());
        
        mappings.put("/transaction/update", new UpdateTransactionController());
        mappings.put("/transaction/view", new ViewTransactionController());
        mappings.put("/transaction/check", new CheckController());
        mappings.put("/transaction/check2", new CheckController());
        
        mappings.put("/transaction/history", new ForwardController("/transaction/history.jsp"));
        mappings.put("/transaction/selldetail", new sellDetailController());
        mappings.put("/transaction/buy", new ListTransactionController());
        mappings.put("/transaction/sell", new ListTransactionController());
        
        mappings.put("/user/wishlist", new ViewWishlistController());
        mappings.put("/user/wishAdd", new AddWishlistController());
        mappings.put("/user/wishDelete", new DeleteWishlistController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	return mappings.get(uri);
    }
}
