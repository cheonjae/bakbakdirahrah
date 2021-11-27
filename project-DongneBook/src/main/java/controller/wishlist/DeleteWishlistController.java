package controller.wishlist;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Wishlist;
import model.service.BookManager;
import model.service.ExistingUserException;
import model.service.WishlistManager;

public class DeleteWishlistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId =  request.getParameter("userId");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		//Wishlist wishlist = new Wishlist(userId, bookId);
		
		WishlistManager wishlistmanager = WishlistManager.getInstance();
		wishlistmanager.delete(userId, bookId);
		

		return null;
	}

}
