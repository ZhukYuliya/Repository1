package command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerForward;
import model.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

/**
 * The Class ShowAllUsersCommand. Shows the paginated list with all the users of NewNet.
 */
public class ShowAllUsersCommand implements Command {
	public static final int SIZE = 10;

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		int page = 1;
		if (request.getParameter(RequestConstants.PAGE) != null){
			page = Integer.valueOf(request.getParameter(RequestConstants.PAGE));
		}
		int size = SIZE;
		int count = 0;
		UserService userService = ServiceFactory.getInstance().getUserService();
		List<User> usersList = null;
		try {
			Pair<List<User>, Integer> usersCountPair = userService.showUsers(page, size);
			if (usersCountPair != null){
				usersList = usersCountPair.getLeft();
				count = usersCountPair.getRight();
			} 
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		int totalPages = count/size + 1;
		request.setAttribute(RequestConstants.USERS_LIST, usersList);
		request.setAttribute(RequestConstants.TOTAL_PAGES, totalPages);

		return new ControllerForward(PageNames.USERS);
	}

}
