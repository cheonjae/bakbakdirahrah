package controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.BookManager;
import model.Book;

public class BookDetailController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(BookDetailController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	BookManager manager = BookManager.getInstance();
	int bookId = Integer.parseInt(request.getParameter("bookId"));

	log.debug("BookDetail's BookId : {}", bookId);

    	Book book = null;
    	try {
		book = manager.findBookDetails(bookId);
	} catch (BookNotFoundException e) {				
	        return "redirect:/user/list";
	}	
		
	// 북 디테일 화면으로 이동(forwarding)
    	request.setAttribute("book", book);					
	return "/user/details.jsp";
    }
}
