package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Book;
import by.newnet.service.TariffService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class ShowTariffs implements Command {

		public static final String CATALOGUE = "catalogue";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		TariffService bookService = ServiceFactory.getInstance().getBookService();
		List<Book> catalogue = null;
		try {
			catalogue = bookService.showCatalogue();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(CATALOGUE, catalogue);
	
		return PageNames.CATALOGUE;
	}

}
