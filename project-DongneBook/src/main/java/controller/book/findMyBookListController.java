package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

public class findMyBookListController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	BookManager manager = BookManager.getInstance();
		String userId = request.getParameter("userId");
		
		List<Book> bookList = manager.findMyBookList(userId);
		
		// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
		request.setAttribute("bookList", bookList);				
		return "/user/mypage.jsp";   
    }
}
