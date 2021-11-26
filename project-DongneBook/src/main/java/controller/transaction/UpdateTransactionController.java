package controller.transaction;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Transaction;
import model.service.TransactionManager;

public class UpdateTransactionController implements Controller{
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		if (request.getMethod().equals("GET")) {	
	    		int bookId = Integer.parseInt(request.getParameter("bookId")); 
	    		String sellerId = request.getParameter("sellerId");
	    		String buyerId = request.getParameter("buyerId");
			
			TransactionManager manager = TransactionManager.getInstance();
//			Transaction transaction = manager.findTransactionDetails(bookId, sellerId, buyerId);	
	
//			request.setAttribute("transaction", transaction);		
			return "/transaction/transactionUpdateForm.jsp";
		}
		
//		int bookId = Integer.parseInt(request.getParameter("bookId"));
//		String sellerId = request.getParameter("sellerId");
//		String buyerId = request.getParameter("buyerId");
		int lastPrice = Integer.parseInt(request.getParameter("lastPrice"));
		String meetingDate = request.getParameter("meetingDate");
		String meetingPlace = request.getParameter("meetingPlace");
		String meetingMemo = request.getParameter("meetingMemo");
		

    	Transaction updateTransaction = new Transaction(lastPrice, meetingDate, meetingPlace, meetingMemo);

    	TransactionManager manager = TransactionManager.getInstance();
		manager.update(updateTransaction);
		
		request.setAttribute("transaction", updateTransaction);

		return "/transaction/transactionView.jsp";
    }	 	
}
