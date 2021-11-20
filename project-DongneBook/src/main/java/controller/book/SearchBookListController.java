package controller.book;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Book;
import model.service.BookManager;

public class SearchBookListController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	BookManager manager = BookManager.getInstance();
		String title = request.getParameter("title");
		
		List<Book> bookList = manager.findMyBookList(title);
		
		request.setAttribute("bookList", bookList);				
		return "/book/bookSearchView.jsp";   
    }
}
