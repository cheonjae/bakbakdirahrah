package controller.wishlist;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Book;
import model.service.WishlistManager;

public class DeleteWishlistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId =  request.getParameter("userId");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		WishlistManager wishlistmanager = WishlistManager.getInstance();
		wishlistmanager.delete(userId, bookId);
		
		List<Book> bookList = wishlistmanager.wishBookList(userId);
		request.setAttribute("wishBookList", bookList);

		return "/user/wishlist.jsp";
	}

}
