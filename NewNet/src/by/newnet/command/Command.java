package by.newnet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;

public interface Command {
		ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
	}



