package controller.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Book;
import model.Transaction;
import model.User;
import model.service.TransactionManager;
import model.service.UserManager;
import model.service.BookManager;

public class ViewTransactionController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ViewTransactionController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 	
    	TransactionManager tmanager = TransactionManager.getInstance();
    	UserManager umanager = UserManager.getInstance();
    	BookManager bmanager = BookManager.getInstance();
    	
    	String userId = request.getParameter("userId");
    	String buddyId = request.getParameter("buddyId");
    	int bookId = Integer.parseInt(request.getParameter("bookId"));
    	
    	log.debug("userId : {}", userId);
    	log.debug("buddyId : {}", buddyId);
    	log.debug("bookId : {}", bookId);
    	
    	Transaction transaction = null;
    	transaction = tmanager.view(bookId, userId, buddyId);
    	
    	User user = null;
	user = umanager.findUser(userId);
		
	log.debug("transaction : {}", transaction);
		
    	request.setAttribute("user", user);			
	request.setAttribute("buddyId", buddyId);
	request.setAttribute("transaction", transaction);
		
	return "/transaction/transactionView.jsp"; 
    }
}
