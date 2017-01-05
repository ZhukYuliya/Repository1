package by.newnet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.exception.CommandException;

public interface Command {
		String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
	}



