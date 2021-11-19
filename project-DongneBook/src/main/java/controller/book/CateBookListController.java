package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

public class CateBookListController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	
    	BookManager manager = BookManager.getInstance();
		String cateId = request.getParameter("cateId");
		
		List<Book> bookList = manager.cateBookList(Integer.parseInt(cateId));
		
		// commList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
		request.setAttribute("bookList", bookList);				
		return "/user/bookCateView.jsp";   
    }
}
