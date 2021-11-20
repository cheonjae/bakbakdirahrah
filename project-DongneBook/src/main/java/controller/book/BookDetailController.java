package controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.BookManager;
import model.service.BookNotFoundException;
import model.Book;

public class BookDetailController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(BookDetailController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	BookManager manager = BookManager.getInstance();
    	int bookId = Integer.parseInt(request.getParameter("bookId"));

        	Book book = null;
        	book = manager.findBookDetails(bookId);	
    		
        	request.setAttribute("book", book);					
    	return "/book/detail.jsp"; 
    }
}
