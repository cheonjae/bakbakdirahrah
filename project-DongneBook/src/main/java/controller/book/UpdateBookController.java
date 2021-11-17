package controller.book;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Book;
import model.User;
import model.sercive.BookManager;
import model.service.UserManager;

public class UpdateBookController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(UpdateBookController.class);
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		if (request.getMethod().equals("GET")) {	
    		
    		String updateId = request.getParameter("bookId"); 
    		String updateUserId = request.getParameter("UserId"); 
    		
    		//log.debug("BookUpdateForm Request : {}", updateId);
 
    		BookManager bookmanager = BookManager.getInstance();
			java.awt.print.Book book = bookmanager.findBookDetails(updateId);	// 수정하려는 책 정보 검색
			request.setAttribute("book", book);		
			
	

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateUserId, session) || //응
				UserSessionUtils.isLoginUser("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
				return "/book/updateForm.jsp";   // 검색한 책정보를 상페로 전송     
			}    
			
			// else (수정 불가능한 경우)
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 책은 수정할 수 없습니다."));            
			return "/book/detail.jsp";	// 상세페이지로이동 (forwarding)
	    }
		
    		
    }	 	

}
