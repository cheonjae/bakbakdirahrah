package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Book;
import model.service.BookManager;

public class MainBookListController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";
        }
    	
    	BookManager manager = BookManager.getInstance();
		List<Book> bookList = manager.mainBookList();
		
		request.setAttribute("bookList", bookList);				
		
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
		return "/user/main.jsp";
    }
}
