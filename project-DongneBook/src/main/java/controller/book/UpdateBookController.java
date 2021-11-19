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
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		if (request.getMethod().equals("GET")) {	
    		
			//update할 책 Id, update 요청한 유저 Id 받아옴. 
    		String updateId = request.getParameter("bookId"); 
    		String updateUserId = request.getParameter("UserId"); 
    		
    		//log.debug("BookUpdateForm Request : {}", updateId);
 
    		BookManager bookmanager = BookManager.getInstance();
    		
    		// 수정하려는 책 정보를 Bookmanager에 findBookDetails를 통해서 불러옴.
			Book book = bookmanager.findBookDetails(Integer.parseInt(updateId));	
			// ↑그 수정하려는 책 정보가 들어있는 book 객체
			request.setAttribute("book", book);		
			

			HttpSession session = request.getSession();
			
			if (UserSessionUtils.isLoginUser(updateUserId, session) || 
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
		
		// POST request. Book 도클에 있는 가격 설명 팔림 생성자 이용함
    	Book updateBook = new Book(
    		Integer.parseInt(request.getParameter("bookId")),
    		Integer.parseInt(request.getParameter("price")),
    		request.getParameter("description"),
    		Integer.parseInt(request.getParameter("sold"))
		);

    	//log.debug("Update User : {}", updateUser);

		BookManager manager = BookManager.getInstance();
		manager.update(updateBook);			
        return "redirect:book/detail"; 
		
    		
    }	 	

}
