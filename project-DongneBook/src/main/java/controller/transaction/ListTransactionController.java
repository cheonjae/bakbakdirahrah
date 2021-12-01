package controller.transaction;

import controller.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.UserSessionUtils;
import model.Book;
import model.Transaction;
import model.service.BookManager;
import model.service.TransactionManager;

public class ListTransactionController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ListTransactionController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 	
		
		// buyList는 트잭 써야해서 트잭에 만들었음
		TransactionManager tmanager = TransactionManager.getInstance();
		List<Transaction> buyList = tmanager.buyList(UserSessionUtils.getLoginUserId(request.getSession()));
		
		// sellList는 그냥 책만 가져오면 돼서 
		BookManager bmanager = BookManager.getInstance();	
		List<Book> sellList = bmanager.sellList(UserSessionUtils.getLoginUserId(request.getSession()));
		
		if(request.getServletPath().equals("/transaction/sell")) {
			request.setAttribute("sellList", sellList);
			return "/transaction/sellList.jsp";
		} else {
			request.setAttribute("buyList", buyList);
			log.debug("transaction : { }", buyList );
			return "/transaction/buyList.jsp";
		}
	}
}
