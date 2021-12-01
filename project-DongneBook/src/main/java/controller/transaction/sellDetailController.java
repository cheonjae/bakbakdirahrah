package controller.transaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Transaction;
import model.service.TransactionManager;

public class sellDetailController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(sellDetailController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	log.debug("1");
    	
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        
        log.debug("bookId : {} ", bookId);
    	
    	TransactionManager manager = TransactionManager.getInstance();
        List<Transaction> sellDetail = manager.sellDetail(UserSessionUtils.getLoginUserId(request.getSession()), bookId);
    		
        	request.setAttribute("bookId", bookId);
        	request.setAttribute("sellDetail", sellDetail);					
    	return "/transaction/sellDetail.jsp"; 
    }
}
