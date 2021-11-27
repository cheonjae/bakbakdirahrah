package controller.transaction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.RegisterUserController;
import model.Transaction;
import model.service.BookManager;
import model.service.TransactionManager;

public class CreateTransactionController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Create Transaction Request");

		TransactionManager manager = TransactionManager.getInstance();
		BookManager bmanager = BookManager.getInstance();
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String sellerId = request.getParameter("sellerId");
		String buyerId = request.getParameter("buyerId");
		int price = bmanager.findBookDetails(bookId).getPrice();

		Transaction transaction = new Transaction(bookId, sellerId, buyerId, price, " ", " ", 0, 0);
		manager.create(transaction);
		request.setAttribute("transaction", transaction);

		return "/transaction/transactionUpdateForm.jsp";
	}
}
