package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Book;
import model.User;
import model.service.BookManager;
import model.service.UserManager;

public class MainBookListController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(MainBookListController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	BookManager bmanager = BookManager.getInstance();
    	HttpSession session = request.getSession();	
    	
    	List<Book> bookList = null;
    	if (!UserSessionUtils.hasLogined(session)) {
    		bookList = bmanager.mainBookList("±¸");
    	}
    	else {
    		String userId = UserSessionUtils.getLoginUserId(request.getSession());
    		
    		UserManager umanager = UserManager.getInstance();
        	User user = umanager.findUser(userId);
        	
    		bookList = bmanager.mainBookList(user.getLocation());
    	} 	
		request.setAttribute("bookList", bookList);			
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));	
	    
		return "/user/main.jsp";
    }
}
