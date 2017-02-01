package by.newnet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.newnet.command.Command;
import by.newnet.command.CommandHelper;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.command.exception.IllegalCommandException;

/**
 * The Class Controller. A unique conroller of the application
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Controller.class);

	private final CommandHelper commandHelper = new CommandHelper();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		String name = request.getParameter(RequestConstants.COMMAND);
		ControllerAction controllerAction = null;
		String contextPath = request.getContextPath();
		Command command = null;
		try {
			command = commandHelper.getCommand(name);
			logger.debug("Starting to execute command " + command);
			controllerAction = command.execute(request, response);
		} catch (IllegalCommandException e) {
			response.sendRedirect(contextPath + PageNames.ERROR_404_PAGE);
			logger.error("Exception was thrown when trying to get non existing command " + command, e);
		} catch (CommandException e) {
			response.sendRedirect(contextPath + PageNames.ERROR_PAGE);
			logger.error("Exception was thrown when trying to execute command " + command, e);
		}
		String url = controllerAction.getUrl();
		/**
		 * Using forward or redirect depending on the class of controllerAction that has come from command.
		 */
		if (controllerAction instanceof ControllerForward) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(contextPath + url);
		}

	}

}
