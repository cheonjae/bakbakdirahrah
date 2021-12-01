package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Book;
import model.User;
import model.service.BookManager;
import model.service.UserManager;

public class SearchBookListController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	BookManager bmanager = BookManager.getInstance();
    	String title = request.getParameter("title");    	
    	HttpSession session = request.getSession();	
    	
    	List<Book> bookList = null;
    	if (!UserSessionUtils.hasLogined(session)) {
    		bookList = bmanager.searchBookList(title, "구");
    	}
    	else {
    		String userId = UserSessionUtils.getLoginUserId(request.getSession());
    		
    		UserManager umanager = UserManager.getInstance();
        	User user = umanager.findUser(userId);
        	
    		bookList = bmanager.searchBookList(title, user.getLocation());
    	} 
		
    	request.setAttribute("bookList", bookList);
    	request.setAttribute("searchWord", title);	
	
    	return "/user/main.jsp";   
    }
}
