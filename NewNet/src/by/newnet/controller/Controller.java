package by.newnet.controller;

import java.io.IOException;

import javax.jws.soap.InitParam;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.CommandHelper;
import by.newnet.command.exception.CommandException;

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

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String name = request.getParameter(COMMAND);
			Command command = commandHelper.getCommand(name);
			String page = null;
			try {
				page = command.execute(request, response);
			} catch (CommandException e) {
			
				throw new ServletException (e);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}

	}

