package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());
    	
    	List<Book> bookList = null;
    	if (userId == null) {
    		bookList = bmanager.mainBookList("구");
    	}
    	else {
    		UserManager umanager = UserManager.getInstance();
        	User user = umanager.findUser(userId);
        	
    		bookList = bmanager.mainBookList(user.getLocation());
    	} 	
		request.setAttribute("bookList", bookList);			
		
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
		return "/user/main.jsp";
    }
}
