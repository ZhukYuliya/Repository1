package by.newnet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;

/**
 * The Interface Command.
 */
public interface Command {
		
		/**
		 * Execute.
		 *
		 * @param request the request
		 * @param response the response
		 * @return the controller action
		 * @throws CommandException the command exception
		 */
		ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
	}



