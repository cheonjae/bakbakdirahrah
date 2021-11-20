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
import model.service.BookManager;
import model.service.UserManager;

public class DeleteBookController implements Controller {
	 private static final Logger log = LoggerFactory.getLogger(DeleteBookController.class);

	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			String deleteId = request.getParameter("bookId");
	    	
//	    	1. Book.userId == userId일 떄 (내책 내가 지움)
//	    	2. 로그인한 사용자가 관리자일 때 (관리자 맘대로 책 지움?)
	    		
			HttpSession session = request.getSession();	
			log.debug("userId : { }", deleteId);
			
			String userId =  UserSessionUtils.getLoginUserId(session);
			
			log.debug("userId : { }", userId);
//		
//			if ((UserSessionUtils.isLoginUser("admin", session) && 	// 로그인한 사용자가 관리자이고 	
//				 !deleteId.equals("admin"))							// 삭제 대상이 일반 사용자인 경우, 
//				   || 												// 또는 
//				(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
//				  UserSessionUtils.isLoginUser(deleteId, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
//					
//				umanager.remove(deleteId);				// 사용자 정보 삭제
//				if (UserSessionUtils.isLoginUser("admin", session))	// 로그인한 사용자가 관리자 	
//					return "redirect:/user/list";		// 사용자 리스트로 이동
//				else 									// 로그인한 사용자는 이미 삭제됨
//					return "redirect:/user/logout";		// logout 처리
//			}

	    	BookManager bmanager = BookManager.getInstance();		
	    	bmanager.deleteBook(Integer.parseInt(deleteId), userId);
	    	
			          
			return "redirect:/user/main";	
		}
}
