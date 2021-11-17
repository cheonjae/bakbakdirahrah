package controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Book;
import model.sercive.BookManager;

import controller.book.RegisterBookController;

public class RegisterBookController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterBookController.class);
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {
       		
       		String updatebookId = request.getParameter("bookId");   //이거 자리 어케함?
       	}
       	
       	if(updatebookId == null) {
       		log.debug("BookUpdateForm Request");
   			return "/book/bookUpdateForm.jsp"; 
       	}
    		//BookRegisterForm 요청
       		log.debug("BookRegisterForm Request");
       			return "/book/bookRegisterForm.jsp";     	
	    }	

       	//book 정보 전달
       	Book book = new Book(
       			request.getParameter("bookId"),
       			request.getParameter("userId"),
       			request.getParameter("cateId"),
       			request.getParameter("title"),
       			request.getParameter("aithor"),
       			request.getParameter("publisher"),
       			request.getParameter("publicationDate"),
       			request.getParameter("price"),
       			request.getParameter("description"),
       			request.getParameter("image"),
       			request.getParameter("sold")
       			);
       	
       	Log.debug("Register Book : {}", book);
       	
       	try {
			BookManager bookmanager = BookManager.getInstance();
			bookmanager.create(book);
	        return "redirect:/book/bookdetail.jsp";	// 성공 시 /book/bookdetail.jsp
	        
		} catch (ExistingBookException e) {	// 예외 발생 시 일단 메인으로 가게함
            request.setAttribute("BookregisterFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("book", book);
			return "/user/main.jsp";
		}
}
