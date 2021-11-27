package controller.transaction;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Transaction;
import model.service.TransactionManager;

public class UpdateTransactionController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(ViewTransactionController.class);
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		if (request.getMethod().equals("GET")) {	
	    	int bookId = Integer.parseInt(request.getParameter("bookId")); 
	    	String sellerId = request.getParameter("sellerId");
	    	String buyerId = request.getParameter("buyerId");
	    	
			TransactionManager manager = TransactionManager.getInstance();
			Transaction transaction = manager.view(bookId, sellerId, buyerId);	
			
			log.debug("bookId: {} sellerId: {} buyerId: {}"
					, bookId, sellerId, buyerId);
			
			request.setAttribute("sellerId", sellerId);
			request.setAttribute("transaction", transaction);		
			return "/transaction/transactionUpdateForm.jsp";
		}
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String sellerId = request.getParameter("sellerId");
		String buyerId = request.getParameter("buyerId");
		int lastPrice = Integer.parseInt(request.getParameter("lastPrice"));
		String meetingDate = request.getParameter("meetingDate");
		String meetingPlace = request.getParameter("meetingPlace");
		String meetingMemo = request.getParameter("meetingMemo");
		
		log.debug("bookId: {} sellerId: {} buyerId: {} lastPrice: {} meetingDate: {} meetingPlace: {} meetingMemo: {}"
				, bookId, sellerId, buyerId, lastPrice, meetingDate, meetingPlace,  meetingMemo);
		

	    TransactionManager manager = TransactionManager.getInstance();
	    Transaction createTransaction = new Transaction(bookId, sellerId, buyerId, lastPrice, meetingDate, meetingPlace, meetingMemo, 0, 0);	
	    Transaction updateTransaction = new Transaction(bookId, sellerId, buyerId, lastPrice, meetingDate, meetingPlace, meetingMemo);
    	
		if(manager.view(bookId, sellerId, buyerId) == null) {
			manager.create(createTransaction);
		}
		else {
			manager.update(updateTransaction);
		}
		
		Transaction transaction = manager.view(bookId, sellerId, buyerId);
		
		request.setAttribute("transaction", transaction);

		return "/transaction/transactionView.jsp";
    }	 	
}
