package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Book;
import by.newnet.domain.Genre;
import by.newnet.service.TariffService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class AddTariff implements Command {

	private static final String DESCRIPTION = "description";
	private static final String GENRE = "genre";
	private static final String ADD_BOOK_MESSAGE = "addBookMessage";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String description;
		String genre;

		description = request.getParameter(DESCRIPTION);
		genre = request.getParameter(GENRE);

		String message = validation(description, genre);

		if (message == null) {
			Book book = new Book();
			book.setDescription(description);
			Genre bookGenre = new Genre();
			bookGenre.setName(genre);
			book.setGenre(bookGenre);

			TariffService bookService = ServiceFactory.getInstance().getBookService();

			try {
				bookService.addBookToCatalogue(book);
				message = "book_added";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}

		request.setAttribute(ADD_BOOK_MESSAGE, message);
		
		return PageNames.SHOW_BOOKS_COMMAND;
	}

	private String validation(String description, String genre) {
		if (StringUtils.isEmpty(description) || StringUtils.isEmpty(genre)) {
			return "empty_fields";
		}
		return null;
	}
}
