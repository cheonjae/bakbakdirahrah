package controller.transaction;

import controller.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Transaction;
import model.User;
import model.service.TransactionManager;
import model.service.UserManager;

public class CheckController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CheckController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 	
    	TransactionManager tmanager = TransactionManager.getInstance();
    	UserManager umanager = UserManager.getInstance();

    	int bookId = Integer.parseInt(request.getParameter("bookId"));
    	String userId = request.getParameter("userId");
    	String buddyId = request.getParameter("buddyId");

    	log.debug("CheckController - bookId : {}", bookId);
    	log.debug("CheckController - userId : {}", userId);
    	log.debug("CheckController - buddyId : {}", buddyId);

    	tmanager.checkUpdate(bookId, userId, buddyId);

    	User user = null;
		user = umanager.findUser(userId);

    	Transaction transaction = null;
    	transaction = tmanager.view(bookId, userId, buddyId);

		log.debug("transaction : {}", transaction);
		
		List<Transaction> sellDetail = tmanager.sellDetail(UserSessionUtils.getLoginUserId(request.getSession()), bookId);

		request.setAttribute("bookId", bookId);

		if(request.getServletPath().equals("/transaction/check")) {
			request.setAttribute("user", user);			
			request.setAttribute("buddyId", buddyId);
			request.setAttribute("transaction", transaction);
			
			return "/transaction/transactionView.jsp"; 
		} else {
			request.setAttribute("sellDetail",  sellDetail);
			
			return "/mypage/sellDetail.jsp";
		}
    }
}
