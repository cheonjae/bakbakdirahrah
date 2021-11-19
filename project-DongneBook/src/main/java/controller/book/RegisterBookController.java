package controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Book;
import model.Category;
import model.service.BookManager;
import model.service.UserManager;
import controller.book.RegisterBookController;

public class RegisterBookController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterBookController.class);
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int updateBookId = -1;
    	if (request.getMethod().equals("GET")) {
    		updateBookId = Integer.parseInt(request.getParameter("bookId"));   //이거 자리 어케함?
       	}
       	
       	if(updateBookId == -1) {
       		log.debug("BookUpdateForm Request");
   			return "/book/bookUpdateForm.jsp"; 
       	} 
       	
       	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("RegisterForm Request");

    		List<Category> cateList = BookManager.getInstance().findCategoryList();	// 커뮤니티 리스트 검색
			request.setAttribute("commList", cateList);	
		
			return "/user/registerForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     	
	    }	
       	
       	//book 정보 전달
       	Book book = new Book(
       			Integer.parseInt(request.getParameter("bookId")),
       			request.getParameter("userId"),
       			request.getParameter("title"),
       			request.getParameter("author"),
       			request.getParameter("publisher"),
       			Integer.parseInt(request.getParameter("price")),
       			request.getParameter("description"),
       			request.getParameter("image"),
       			Integer.parseInt(request.getParameter("sold")),
       			Integer.parseInt(request.getParameter("cateId")),
       			Integer.parseInt(request.getParameter("pageDiscoloration")),
       			Integer.parseInt(request.getParameter("coverDamage")),
       			Integer.parseInt(request.getParameter("pageDamage")),
       			Integer.parseInt(request.getParameter("writing")
       			));
       	
       	log.debug("Register Book : {}", book);
       	
//       	try {
			BookManager bookmanager = BookManager.getInstance();
			bookmanager.create(book);
	        return "redirect:/book/bookdetail.jsp";	// 성공 시 /book/bookdetail.jsp
	        
//		} catch (BookInfoException e) {	// 예외 발생 시 일단 메인으로 가게함
//            request.setAttribute("BookregisterFailed", true);
//			request.setAttribute("exception", e);
//			request.setAttribute("book", book);
//			return "/user/main.jsp";
//		}
       	
    }
}
