package controller.book;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Book;

import model.service.BookManager;

public class UpdateBookController implements Controller{
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		if (request.getMethod().equals("GET")) {	
	    		String updateId = request.getParameter("bookId"); 
			
			BookManager bookmanager = BookManager.getInstance();
			Book book = bookmanager.findBookDetails(Integer.parseInt(updateId));	
	
			request.setAttribute("book", book);		
			return "/book/updateForm.jsp";
		}
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int price = Integer.parseInt(request.getParameter("price"));
		String description = request.getParameter("description");
		int sold = Integer.parseInt(request.getParameter("sold"));
		

    	Book updateBook = new Book(bookId, price, description, sold);

		BookManager manager = BookManager.getInstance();
		manager.update(updateBook);
		
		Book book = manager.findBookDetails(bookId);
		request.setAttribute("book", book);

		return "/book/detail.jsp";
    }	 	
}
