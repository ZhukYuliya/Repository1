package by.newnet.command.impl;

import by.newnet.command.CommandName;

public class PageNames {
	public static final String INDEX = "/index.jsp";
	public static final String HOME = "/home.jsp";
	public static final String ADMIN = "/admin.jsp";
	public static final String CATALOGUE = "/catalogue.jsp";
	public static final String OPERATOR = "/operator.jsp";
	public static final String PERSONAL_DETAILS = "/personal_details.jsp";

	public static final String SHOW_ACCOUNT_COMMAND = "/controller?command=" + CommandName.SHOW_ACCOUNT_INFO;
	public static final String CHANGE_PERSONAL_DETAILS_COMMAND = "/controller?command=" + CommandName.CHANGE_PERSONAL_DETAILS;

	
	private PageNames(){}
	

}
