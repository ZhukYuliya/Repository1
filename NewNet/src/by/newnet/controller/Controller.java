package by.newnet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.CommandHelper;
import by.newnet.command.exception.CommandException;
import by.newnet.command.exception.IllegalCommandException;
import by.newnet.command.impl.PageNames;

/*@WebServlet(name="Controller",
urlPatterns={"/myurl"},
initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2")})*/
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandHelper commandHelper = new CommandHelper();
	public static final String COMMAND = "command";

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		String name = request.getParameter(COMMAND);
		Command command = null;
		ControllerAction controllerAction = null;
		try {
			command = commandHelper.getCommand(name);
			controllerAction = command.execute(request, response);
			// illegal command exc too?
		} catch (IllegalCommandException e) {
			response.sendRedirect(PageNames.ERROR_404_PAGE);
		} catch (CommandException e) {
			// page,not exception
			throw new ServletException(e);
			// log??
		}
		String url = controllerAction.getUrl();
		if (controllerAction instanceof ControllerForward) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(url);
		}

	}

}
