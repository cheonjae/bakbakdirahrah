package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Book;
import model.User;
import model.service.BookManager;
import model.service.UserManager;

public class CateBookListController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	BookManager bmanager = BookManager.getInstance();
		String cateId = request.getParameter("cateId");
		
    	String userId = UserSessionUtils.getLoginUserId(request.getSession());

    	List<Book> bookList = null;
    	if (userId == null) {
    		bookList = bmanager.cateBookList(Integer.parseInt(cateId), "구");
    	}
    	else {
    		UserManager umanager = UserManager.getInstance();
        	User user = umanager.findUser(userId);
        	
    		bookList = bmanager.cateBookList(Integer.parseInt(cateId), user.getLocation());
    	} 
		
		// bookList 객체를 request에 저장하여 메인 화면으로 이동(forwarding)
		request.setAttribute("bookList", bookList);				
		return "/user/main.jsp";   
    }
}
