package controller.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.book.DeleteBookController;
import controller.user.UserSessionUtils;
import model.Book;
import model.User;
import model.service.TransactionManager;

public class DeleteTransactionController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(DeleteTransactionController.class);
	 	@Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	 	
	 		int deletebookId = Integer.parseInt(request.getParameter("bookId"));
	 		String deletesellerId = request.getParameter("sellerId");
	 		String deletbuyerId = request.getParameter("buyerId");
	 
	 
	 		TransactionManager tmanager = TransactionManager.getInstance();
	 		tmanager.deleteTransaction(deletebookId, deletesellerId, deletbuyerId); 
		 return "redirect:/user/mypage";
	 }
}
