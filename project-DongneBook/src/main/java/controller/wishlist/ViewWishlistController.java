package controller.wishlist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.chat.CreateChatController;
import controller.user.UserSessionUtils;
import model.Book;
import model.service.WishlistManager;

public class ViewWishlistController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateChatController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		WishlistManager manager = WishlistManager.getInstance();
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		log.debug("wishlist: user id = "+ userId);
		
		List<Book> bookList = manager.wishBookList(userId);
		for (Book book : bookList)
			System.out.println(book.getTitle());

		request.setAttribute("wishBookList", bookList);
		
		return "/user/wishlist.jsp";
	}

}
